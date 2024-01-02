package qu4lizz.ip.fitness.admin.models;

import java.sql.Timestamp;
import java.util.Objects;

public class LogEntity {
    private Long id;
    private String text;
    private Timestamp datetime;

    public LogEntity(Long id, String text, Timestamp timestamp) {
        this.id = id;
        this.text = text;
        this.datetime = timestamp;
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
        return Objects.equals(id, logEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, datetime);
    }
}
