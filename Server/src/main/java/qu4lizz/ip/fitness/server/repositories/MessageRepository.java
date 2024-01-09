package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
}
