package qu4lizz.ip.fitness.server.models.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private byte[] image;
    private Boolean verified;
    private Boolean active;
}
