package qu4lizz.ip.fitness.server.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qu4lizz.ip.fitness.server.models.entities.ActivityEntity;
import qu4lizz.ip.fitness.server.models.requests.ActivityCreateRequest;
import qu4lizz.ip.fitness.server.services.ActivityService;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/user/{id}")
    public List<ActivityEntity> getActivitiesOfUser(@PathVariable Integer id) {
        return activityService.getActivitiesOfUser(id);
    }

    @PostMapping
    public void addActivity(@RequestBody ActivityCreateRequest request) {
        activityService.addActivity(request);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> getPDF(@PathVariable Integer id) {
        activityService.generatePDF(id);

        try {
            InputStream inputStream = new FileInputStream("table.pdf");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }
}
