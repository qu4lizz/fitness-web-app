package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import qu4lizz.ip.fitness.server.models.entities.ChatEntity;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    @Query("SELECT c FROM ChatEntity c JOIN c.messages m WHERE c.userOne.id = :userId OR c.userTwo.id = :userId ORDER BY m.timestamp DESC")
    List<ChatEntity> findAllByUserOneOrUserTwoOrderByMessages(@Param("userId") Integer userId);

}
