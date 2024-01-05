package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user", schema = "public", catalog = "fitness")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "image")
    private byte[] image;
    @Basic
    @Column(name = "verified")
    private Boolean verified;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "idUser")
    private List<ActivityEntity> activities;
    @OneToMany(mappedBy = "instructor")
    private List<ProgramEntity> createdPrograms;
    @OneToMany(mappedBy = "idUser")
    private List<UserParticipatesProgramEntity> userParticipatedPrograms;
    @OneToMany(mappedBy = "idUser")
    private List<UserSubscribeCategoryEntity> userSubscribedCategories;

    public UserEntity() {}

    public UserEntity(String name, String surname, String username, String password, String mail, String city, MultipartFile image) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.mail = mail;
        this.username = username;
        this.password = password;
        active = true;
        verified = false;
        try {
            if (image != null)
                this.image = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(city, that.city) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(mail, that.mail) && Arrays.equals(image, that.image) && Objects.equals(verified, that.verified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, surname, city, username, password, mail, verified);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
