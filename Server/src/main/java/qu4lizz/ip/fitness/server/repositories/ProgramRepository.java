package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.ProgramEntity;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {
    Page<ProgramEntity> findAllByCategoryId(Pageable page, Integer categoryId);

    Page<ProgramEntity> findAllByDifficultyId(Pageable page, Integer difficultyId);

    Page<ProgramEntity> findAllByDifficultyIdAndCategoryId(Pageable page, Integer difficultyId, Integer categoryId);
}
