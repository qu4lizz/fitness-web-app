package qu4lizz.ip.fitness.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qu4lizz.ip.fitness.server.models.responses.RssResponse;
import qu4lizz.ip.fitness.server.services.RssService;

@RestController
@RequestMapping("/api/rss")
public class RssController {
    private final RssService rssService;

    public RssController(RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping
    public RssResponse getRssFeed() {
        return rssService.getRssFeed();
    }
}
