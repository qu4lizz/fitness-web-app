package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_comment_program", schema = "public", catalog = "fitness")
public class UserCommentProgramEntity {
    @Basic
    @Column(name = "id_user")
    private Integer idUser;
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCommentProgramEntity that = (UserCommentProgramEntity) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idProgram, that.idProgram) && Objects.equals(id, that.id) && Objects.equals(comment, that.comment) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idProgram, id, comment, timestamp);
    }
}
