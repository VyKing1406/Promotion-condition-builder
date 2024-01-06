package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "promotion_category", schema = "public", catalog = "promotion_fresher")
public class PromotionCategoryEntity {
    @Id
    @Column(name = "promotion_category_id")
    private UUID promotionCategoryId;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Basic
    @Column(name = "min_item")
    private Integer minItem;

    @Basic
    @Column(name = "max_item")
    private Integer maxItem;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private CategoryEntity categoryByCategoryId;
}
