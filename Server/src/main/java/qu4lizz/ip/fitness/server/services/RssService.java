package qu4lizz.ip.fitness.server.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import qu4lizz.ip.fitness.server.models.responses.RssResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

@Service
public class RssService {
    private final String url;

    public RssService() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(new ClassPathResource(MailService.CONFIG_PATH).getFile()));

        url = properties.getProperty("rss_feed_url");
    }

    public RssResponse getRssFeed() {
        try {
            String content = readURL(url);

            return new RssResponse(content);
        } catch (IOException e) {
            throw new RuntimeException("Could not read RSS");
        }
    }

    private String readURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }

        return contentBuilder.toString();
    }
}
