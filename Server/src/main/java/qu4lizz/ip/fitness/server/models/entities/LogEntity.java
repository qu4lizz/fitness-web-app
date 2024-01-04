package qu4lizz.ip.fitness.server.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "log", schema = "public", catalog = "fitness")
public class LogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "datetime")
    private Timestamp datetime;

    public LogEntity() {}

    public LogEntity(String text, Timestamp datetime) {
        this.text = text;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return Objects.equals(id, logEntity.id) && Objects.equals(text, logEntity.text) && Objects.equals(datetime, logEntity.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, datetime);
    }
}
