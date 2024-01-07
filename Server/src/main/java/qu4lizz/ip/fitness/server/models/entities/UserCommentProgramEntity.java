package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_comment_program", schema = "public", catalog = "fitness")
public class UserCommentProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "timestamp")
    private Instant timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCommentProgramEntity that = (UserCommentProgramEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgram, id, comment, timestamp);
    }
}
