package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "chat", schema = "public", catalog = "fitness")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity userOne;
    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id", nullable = false)
    private UserEntity userTwo;
    @OneToMany(mappedBy = "idChat")
    private List<MessageEntity> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
