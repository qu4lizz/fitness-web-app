package qu4lizz.ip.fitness.counselingapp.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Advice implements Serializable {
    private Integer id;
    private String message;
    private Timestamp timestamp;
    private Boolean isRead;
    private User user;

    public Advice(int id, String message, Timestamp timestamp, boolean isRead, User user) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
