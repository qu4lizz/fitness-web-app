package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.UserParticipatesProgramEntity;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<UserParticipatesProgramEntity, Integer> {
    UserParticipatesProgramEntity findByIdProgramAndIdUser(Integer idProgram, Integer idUser);

    List<UserParticipatesProgramEntity> findAllByIdUser(Integer idUser);

}
