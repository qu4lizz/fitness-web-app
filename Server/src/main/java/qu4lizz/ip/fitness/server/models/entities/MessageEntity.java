package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

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
    @Column(name = "timstamp")
    private Timestamp timstamp;
    @Basic
    @Column(name = "is_read")
    private Boolean isRead;
    @Basic
    @Column(name = "sender_id")
    private Integer senderId;
    @Basic
    @Column(name = "id_chat")
    private Integer idChat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimstamp() {
        return timstamp;
    }

    public void setTimstamp(Timestamp timstamp) {
        this.timstamp = timstamp;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && Objects.equals(timstamp, that.timstamp) && Objects.equals(isRead, that.isRead) && Objects.equals(senderId, that.senderId) && Objects.equals(idChat, that.idChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timstamp, isRead, senderId, idChat);
    }
}
