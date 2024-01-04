package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {

}
