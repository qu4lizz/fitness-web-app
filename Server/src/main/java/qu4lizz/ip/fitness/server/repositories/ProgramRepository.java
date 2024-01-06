package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.ProgramEntity;

import java.time.Instant;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {
    Page<ProgramEntity> findAll(Specification<ProgramEntity> spec, Pageable pageable);
}
