package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_subscribe_category", schema = "public", catalog = "fitness")
public class UserSubscribeCategoryEntity {
    @Basic
    @Column(name = "id_user")
    private Integer idUser;
    @Basic
    @Column(name = "id_category")
    private Integer idCategory;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscribeCategoryEntity that = (UserSubscribeCategoryEntity) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idCategory, that.idCategory) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idCategory, id);
    }
}
