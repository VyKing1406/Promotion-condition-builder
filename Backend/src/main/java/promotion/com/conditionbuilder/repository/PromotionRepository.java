package promotion.com.conditionbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.PromotionEntity;

import java.util.UUID;


@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, UUID>{

}