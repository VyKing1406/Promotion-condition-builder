package promotion.com.conditionbuilder.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import promotion.com.conditionbuilder.entity.ProductEntity;
import promotion.com.conditionbuilder.entity.PromotionEntity;
import promotion.com.conditionbuilder.entity.PromotionProductEntity;

public interface PromotionProductRepository extends JpaRepository<PromotionProductEntity, UUID> {
    List<PromotionProductEntity> findByPromotionByPromotionId(PromotionEntity promotionByPromotionId);
    @Modifying
    @Query(value = "DELETE FROM promotion_product pp WHERE pp.promotion_id = :id", nativeQuery = true)
    void deletePromotionProduct(@Param("id") UUID id);

    void deleteByPromotionByPromotionId(PromotionEntity promotionEntity);
}