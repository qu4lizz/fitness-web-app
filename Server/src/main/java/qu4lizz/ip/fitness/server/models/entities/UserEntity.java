package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

    public List<ProgramEntity> getCreatedPrograms() {
        return createdPrograms;
    }

    public void setCreatedPrograms(List<ProgramEntity> createdPrograms) {
        this.createdPrograms = createdPrograms;
    }

    public List<UserParticipatesProgramEntity> getUserParticipatedPrograms() {
        return userParticipatedPrograms;
    }

    public void setUserParticipatedPrograms(List<UserParticipatesProgramEntity> userParticipatedPrograms) {
        this.userParticipatedPrograms = userParticipatedPrograms;
    }

    public List<UserSubscribeCategoryEntity> getUserSubscribedCategories() {
        return userSubscribedCategories;
    }

    public void setUserSubscribedCategories(List<UserSubscribeCategoryEntity> userSubscribedCategories) {
        this.userSubscribedCategories = userSubscribedCategories;
    }
}
