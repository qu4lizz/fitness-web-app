package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.UserCommentProgramEntity;

public interface CommentRepository extends JpaRepository<UserCommentProgramEntity, Integer> {
}
