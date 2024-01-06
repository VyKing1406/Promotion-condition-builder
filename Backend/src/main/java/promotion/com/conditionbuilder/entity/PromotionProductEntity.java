package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "promotion_product", schema = "public", catalog = "promotion_fresher")
public class PromotionProductEntity {
    @Id
    @Column(name = "promotion_item_id")
    private UUID id;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Basic
    @Column(name = "min_quantity")
    private Integer minQuantity;

    @Basic
    @Column(name = "max_quantity")
    private Integer maxQuantity;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private ProductEntity productByProductId;
    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id")
    private PromotionEntity promotionByPromotionId;
}
