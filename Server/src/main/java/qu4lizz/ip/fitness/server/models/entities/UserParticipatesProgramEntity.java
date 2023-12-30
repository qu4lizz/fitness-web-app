package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_participates_program", schema = "public", catalog = "fitness")
public class UserParticipatesProgramEntity {
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
    @Column(name = "payment_type")
    private String paymentType;

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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

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
