package qu4lizz.ip.fitness.server.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Data
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
    private Integer duration;
    @Basic
    @Column(name = "start")
    private Instant start;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "video_url")
    private String videoUrl;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @Basic
    @Column(name = "created_at")
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_difficulty", referencedColumnName = "id", nullable = false)
    private DifficultyEntity difficulty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity instructor;
    @OneToMany(mappedBy = "idProgram")
    private List<ProgramImageEntity> programImages;
    @OneToMany(mappedBy = "idProgram")
    private List<UserCommentProgramEntity> comments;
    @OneToMany(mappedBy = "idProgram")
    private List<UserParticipatesProgramEntity> participation;

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
}
