package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "program", schema = "public", catalog = "fitness")
public class ProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "duration")
    private Object duration;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "video_url")
    private String videoUrl;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "id_difficulty", referencedColumnName = "id", nullable = false)
    private DifficultyEntity difficulty;
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity instructor;
    @OneToMany(mappedBy = "idProgram")
    private List<ProgramImageEntity> programImages;
    @OneToMany(mappedBy = "idProgram")
    private List<UserCommentProgramEntity> comments;
    @OneToMany(mappedBy = "idProgram")
    private List<UserParticipatesProgramEntity> participation;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
        ProgramEntity that = (ProgramEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(duration, that.duration) && Objects.equals(location, that.location) && Objects.equals(videoUrl, that.videoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, duration, location, videoUrl);
    }

    public DifficultyEntity getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEntity difficulty) {
        this.difficulty = difficulty;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(UserEntity instructor) {
        this.instructor = instructor;
    }

    public List<ProgramImageEntity> getProgramImages() {
        return programImages;
    }

    public void setProgramImages(List<ProgramImageEntity> programImages) {
        this.programImages = programImages;
    }

    public List<UserCommentProgramEntity> getComments() {
        return comments;
    }

    public void setComments(List<UserCommentProgramEntity> comments) {
        this.comments = comments;
    }

    public List<UserParticipatesProgramEntity> getParticipation() {
        return participation;
    }

    public void setParticipation(List<UserParticipatesProgramEntity> participation) {
        this.participation = participation;
    }
}
