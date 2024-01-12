package qu4lizz.ip.fitness.server.services;


import com.aspose.pdf.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.entities.ActivityEntity;
import qu4lizz.ip.fitness.server.models.requests.ActivityCreateRequest;
import qu4lizz.ip.fitness.server.repositories.ActivityRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter;
    private final LogService logService;

    public ActivityService(ActivityRepository activityRepository, ModelMapper modelMapper, LogService logService) {
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
        this.logService = logService;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm")
                .withZone(ZoneId.systemDefault());
    }

    public List<ActivityEntity> getActivitiesOfUser(Integer id) {
        logService.log("Fetching all activites of user with id: " + id);
        return activityRepository.findAllByIdUserOrderByTimestamp(id);
    }

    public void addActivity(ActivityCreateRequest request) {
        ActivityEntity activityEntity = modelMapper.map(request, ActivityEntity.class);

        activityRepository.save(activityEntity);
        logService.log("User with id " + request.getIdUser() + " adding new activity");
    }

    public void generatePDF(Integer id) {
        List<ActivityEntity> activities = activityRepository.findAllByIdUserOrderByTimestamp(id);

        logService.log("User with id " + id + " downloading ActivityPDF");

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Document doc = new Document()) {

            Page page = doc.getPages().add();

            Table table = new Table();

            page.getParagraphs().add(table);

            table.setColumnWidths("90 90 90 90 90");

            table.setDefaultCellBorder(new BorderInfo(BorderSide.All, 0.1F));

            table.setBorder(new BorderInfo(BorderSide.All, 1F));

            MarginInfo margin = new MarginInfo();
            margin.setTop(5f);
            margin.setLeft(5f);
            margin.setRight(5f);
            margin.setBottom(5f);

            table.setDefaultCellPadding(margin);


            Row header = table.getRows().add();

            header.getCells().add("Timestamp");
            header.getCells().add("Exercise Type");
            header.getCells().add("Duration (minutes)");
            header.getCells().add("Intensity");
            header.getCells().add("Result (kilogram)");

            for (var elem : activities) {
                Row row = table.getRows().add();

                row.getCells().add(formatter.format(elem.getTimestamp()));
                row.getCells().add(elem.getExerciseType());
                row.getCells().add(elem.getDuration());
                row.getCells().add(elem.getIntensity());
                row.getCells().add(elem.getResult());
            }


            doc.save("table.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
