package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "program_has_attributes", schema = "public", catalog = "fitness")
public class ProgramHasAttributesEntity {
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "value")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_attribute", referencedColumnName = "id", nullable = false)
    private AttributeEntity attribute;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramHasAttributesEntity that = (ProgramHasAttributesEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgram, id, value);
    }
}
