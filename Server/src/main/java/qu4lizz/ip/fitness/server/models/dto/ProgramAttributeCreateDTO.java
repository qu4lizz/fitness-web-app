package qu4lizz.ip.fitness.server.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramAttributeCreateDTO {
    private Integer id;
    private Integer idProgram;
    private String value;
    private Integer idAttribute;

    public ProgramAttributeCreateDTO(Integer idA, String val, Integer idP) {
        id = null;
        idProgram = idP;
        idAttribute = idA;
        value = val;
    }
}
