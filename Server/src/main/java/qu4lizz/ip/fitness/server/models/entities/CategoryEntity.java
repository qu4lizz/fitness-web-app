package qu4lizz.ip.fitness.server.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "category", schema = "public", catalog = "fitness")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "idCategory")
    private List<AttributeEntity> attributes;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProgramEntity> programs;
    @JsonIgnore
    @OneToMany(mappedBy = "idCategory")
    private List<UserSubscribeCategoryEntity> userSubscribeCategories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
