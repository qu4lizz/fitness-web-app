package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "user_participates_program", schema = "public", catalog = "fitness")
public class UserParticipatesProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "id_user")
    private Integer idUser;
    @Basic
    @Column(name = "id_program")
    private Integer idProgram;
    @Basic
    @Column(name = "payment_type")
    private String paymentType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserParticipatesProgramEntity that = (UserParticipatesProgramEntity) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idProgram, that.idProgram) && Objects.equals(id, that.id) && Objects.equals(paymentType, that.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idProgram, id, paymentType);
    }
}
