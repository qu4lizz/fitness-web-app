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
import qu4lizz.ip.fitness.server.models.responses.CategoryResponse;
import qu4lizz.ip.fitness.server.models.responses.SubscribedCategoriesResponse;
import qu4lizz.ip.fitness.server.repositories.CategoryRepository;
import qu4lizz.ip.fitness.server.repositories.UserSubscribeCategoryRepository;

import java.time.Instant;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final UserSubscribeCategoryRepository userSubscribeCategoryRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository repository, UserSubscribeCategoryRepository userSubscribeCategoryRepository, UserService userService, ModelMapper modelMapper) {
        this.repository = repository;
        this.userSubscribeCategoryRepository = userSubscribeCategoryRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public List<CategoryResponse> findAll() {
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
        }
        else if (existing != null) {
            userSubscribeCategoryRepository.delete(existing);
        }
        else {
            throw new BadRequestException("Can't subscribe because not subscribed");
        }
    }

    public List<SubscribedCategoriesResponse> getSubsribedCategories(Integer id) {
        var entities = userSubscribeCategoryRepository.findAllByIdUser(id);

        return entities.stream().map(e -> modelMapper.map(e, SubscribedCategoriesResponse.class)).toList();
    }

    @Transactional
    @Scheduled(cron = "0 48 13 * * *")
    public void sendDailyCategoryEmails() {
        System.out.println("doing cron");

        List<CategoryEntity> categories = repository.findAll();

        Instant yesterday = Instant.now().minusSeconds(86400);

        for (var category : categories) {
            if (!category.getUserSubscribeCategories().isEmpty()) {
                List<ProgramEntity> newPrograms = category.getPrograms().stream().filter(p -> p.getCreatedAt().isAfter(yesterday)).toList();
                if (!newPrograms.isEmpty()) {
                    for(var userSubscription : category.getUserSubscribeCategories()) {
                        userService.sendDailyCategoryEmails(userSubscription.getIdUser(), newPrograms);
                    }
                }
            }
        }
        System.out.println("finished cron");

    }
}
