package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "program_has_attributes", schema = "public", catalog = "fitness")
public class ProgramHasAttributesEntity {
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;
    @Basic
    @Column(name = "id_attribute")
    private Integer idAttribute;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "value")
    private String value;

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public Integer getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Integer idAttribute) {
        this.idAttribute = idAttribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramHasAttributesEntity that = (ProgramHasAttributesEntity) o;
        return Objects.equals(idProgram, that.idProgram) && Objects.equals(idAttribute, that.idAttribute) && Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgram, idAttribute, id, value);
    }
}
