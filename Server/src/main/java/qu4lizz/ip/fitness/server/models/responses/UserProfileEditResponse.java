package qu4lizz.ip.fitness.server.models.responses;

import lombok.Data;

@Data
public class UserProfileEditResponse {
    private Integer id;
    private String name;
    private String surname;
    private String city;
    private String username;
    private String mail;
    private byte[] image;
    private Boolean verified;
}
