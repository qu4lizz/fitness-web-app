package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.AttributeEntity;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {
    List<AttributeEntity> findAllByIdCategory(Integer idCategory);
}
