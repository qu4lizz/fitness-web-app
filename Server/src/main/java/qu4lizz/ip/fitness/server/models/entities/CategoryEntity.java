package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "category")
    private List<ProgramEntity> programs;
    @OneToMany(mappedBy = "idCategory")
    private List<UserSubscribeCategoryEntity> userSubscribeCategories;

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

    public List<AttributeEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeEntity> attributes) {
        this.attributes = attributes;
    }

    public List<ProgramEntity> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramEntity> programs) {
        this.programs = programs;
    }

    public List<UserSubscribeCategoryEntity> getUserSubscribeCategories() {
        return userSubscribeCategories;
    }

    public void setUserSubscribeCategories(List<UserSubscribeCategoryEntity> userSubscribeCategories) {
        this.userSubscribeCategories = userSubscribeCategories;
    }
}
