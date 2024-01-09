package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "message", schema = "public", catalog = "fitness")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Basic
    @Column(name = "is_read")
    private Boolean isRead;
    @Basic
    @Column(name = "sender_id")
    private Integer senderId;
    @Basic
    @Column(name = "id_chat")
    private Integer idChat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp) && Objects.equals(isRead, that.isRead) && Objects.equals(senderId, that.senderId) && Objects.equals(idChat, that.idChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timestamp, isRead, senderId, idChat);
    }
}
