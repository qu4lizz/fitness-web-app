package qu4lizz.ip.fitness.server.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.RssResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssControler {
    @GetMapping
    public RssResponse getRssFeed() {
        try {
            System.out.println("aee");
            List<String> lines = Files.readAllLines(new ClassPathResource("rss.xml").getFile().toPath());
            return new RssResponse(lines.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
