package qu4lizz.ip.fitness.server.services;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.entities.CategoryEntity;
import qu4lizz.ip.fitness.server.models.entities.ProgramEntity;
import qu4lizz.ip.fitness.server.models.entities.UserSubscribeCategoryEntity;
import qu4lizz.ip.fitness.server.models.requests.CategorySubscribeRequest;
import qu4lizz.ip.fitness.server.models.responses.AttributeResponse;
import qu4lizz.ip.fitness.server.models.responses.CategoryResponse;
import qu4lizz.ip.fitness.server.models.responses.SubscribedCategoriesResponse;
import qu4lizz.ip.fitness.server.repositories.AttributeRepository;
import qu4lizz.ip.fitness.server.repositories.CategoryRepository;
import qu4lizz.ip.fitness.server.repositories.UserSubscribeCategoryRepository;

import java.time.Instant;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final AttributeRepository attributeRepository;
    private final UserSubscribeCategoryRepository userSubscribeCategoryRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final LogService logService;

    public CategoryService(CategoryRepository repository, AttributeRepository attributeRepository, UserSubscribeCategoryRepository userSubscribeCategoryRepository, UserService userService, ModelMapper modelMapper, LogService logService) {
        this.repository = repository;
        this.attributeRepository = attributeRepository;
        this.userSubscribeCategoryRepository = userSubscribeCategoryRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.logService = logService;
    }


    public List<CategoryResponse> findAll() {
        logService.log("Fetching all categories");
        List<CategoryEntity> entities = repository.findAll();
        return entities.stream()
                .map(CategoryResponse::new)
                .toList();
    }


    public void subscribeUserToCategory(CategorySubscribeRequest request) throws BadRequestException {
        UserSubscribeCategoryEntity existing = userSubscribeCategoryRepository.findByIdCategoryAndIdUser(request.getIdCategory(), request.getIdUser());

        if (existing != null && request.getSubscribe()) {
            throw new BadRequestException("Already subscribed");
        }
        else if (existing == null && request.getSubscribe()) {
            UserSubscribeCategoryEntity entity = modelMapper.map(request, UserSubscribeCategoryEntity.class);

            userSubscribeCategoryRepository.save(entity);
            logService.log("User with id " + request.getIdUser() + " subscribed to category with id " + request.getIdCategory());
        }
        else if (existing != null) {
            userSubscribeCategoryRepository.delete(existing);
            logService.log("User with id " + request.getIdUser() + " unsubscribed to category with id " + request.getIdCategory());
        }
        else {
            throw new BadRequestException("Can't subscribe because not subscribed");
        }
    }

    public List<SubscribedCategoriesResponse> getSubscribedCategories(Integer id) {
        logService.log("Fetching subscribed categories of user with id " + id);
        var entities = userSubscribeCategoryRepository.findAllByIdUser(id);

        return entities.stream().map(e -> modelMapper.map(e, SubscribedCategoriesResponse.class)).toList();
    }

    @Transactional
    @Scheduled(cron = "0 45 13 * * *")
    public void sendDailyCategoryEmails() {
        logService.log("Sending daily subscription emails");

        int sentEmails = 0;

        List<CategoryEntity> categories = repository.findAll();

        Instant yesterday = Instant.now().minusSeconds(86400);

        for (var category : categories) {
            if (!category.getUserSubscribeCategories().isEmpty()) {
                List<ProgramEntity> newPrograms = category.getPrograms().stream().filter(p -> p.getCreatedAt().isAfter(yesterday)).toList();
                if (!newPrograms.isEmpty()) {
                    for(var userSubscription : category.getUserSubscribeCategories()) {
                        userService.sendDailyCategoryEmails(userSubscription.getIdUser(), newPrograms);
                        sentEmails++;
                    }
                }
            }
        }

        logService.log("Sent " + sentEmails + " subscription emails");
    }

    public List<AttributeResponse> getCategoryAttributes(Integer categoryId) {
        var attributes = attributeRepository.findAllByIdCategory(categoryId);

        return attributes.stream().map(a -> modelMapper.map(a, AttributeResponse.class)).toList();
    }
}
