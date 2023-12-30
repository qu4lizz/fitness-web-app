package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "attribute", schema = "public", catalog = "fitness")
public class AttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "value")
    private String value;
    @Basic
    @Column(name = "id_category")
    private Integer idCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeEntity that = (AttributeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(value, that.value) && Objects.equals(idCategory, that.idCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, idCategory);
    }
}
