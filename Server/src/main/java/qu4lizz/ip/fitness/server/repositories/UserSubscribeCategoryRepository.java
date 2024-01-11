package qu4lizz.ip.fitness.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import qu4lizz.ip.fitness.server.models.entities.UserSubscribeCategoryEntity;

import java.util.List;

public interface UserSubscribeCategoryRepository extends JpaRepository<UserSubscribeCategoryEntity, Integer> {
    UserSubscribeCategoryEntity findByIdCategoryAndIdUser(Integer idCategory, Integer idUser);

    List<UserSubscribeCategoryEntity> findAllByIdUser(Integer idUser);
}
