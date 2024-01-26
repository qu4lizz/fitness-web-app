package qu4lizz.ip.fitness.server.models.responses;

import lombok.Data;

@Data
public class RssResponse {
    private String value;

    public RssResponse(String value) {
        this.value = value;
    }
}
