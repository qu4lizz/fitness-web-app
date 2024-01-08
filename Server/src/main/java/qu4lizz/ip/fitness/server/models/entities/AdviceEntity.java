package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Data
@Entity
@Table(name = "advice", schema = "public", catalog = "fitness")
public class AdviceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "timestamp")
    private Instant timestamp;
    @Basic
    @Column(name = "is_read")
    private Boolean isRead;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdviceEntity that = (AdviceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp) && Objects.equals(isRead, that.isRead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timestamp, isRead);
    }

}
