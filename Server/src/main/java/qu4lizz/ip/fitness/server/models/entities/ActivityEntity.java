package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
@Entity
@Table(name = "activity", schema = "public", catalog = "fitness")
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "exercise_type")
    private String exerciseType;
    @Basic
    @Column(name = "duration")
    private String duration;
    @Basic
    @Column(name = "intensity")
    private String intensity;
    @Basic
    @Column(name = "result")
    private String result;
    @Basic
    @Column(name = "timestamp")
    private Instant timestamp;
    @Basic
    @Column(name = "id_user")
    private Integer idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(exerciseType, that.exerciseType) && Objects.equals(duration, that.duration) && Objects.equals(intensity, that.intensity) && Objects.equals(result, that.result) && Objects.equals(timestamp, that.timestamp) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseType, duration, intensity, result, timestamp, idUser);
    }
}
