package qu4lizz.ip.fitness.server.services;

import qu4lizz.ip.fitness.server.models.entities.LogEntity;
import qu4lizz.ip.fitness.server.repositories.LogRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogService {
    private final LogRepository repository;

    public LogService(LogRepository repository) {
        this.repository = repository;
    }
    public void log(String message) {
        repository.save(new LogEntity(message, new Timestamp(System.currentTimeMillis())));
    }
}
