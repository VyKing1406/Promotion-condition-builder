package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "promotion_manufacturer", schema = "public", catalog = "promotion_fresher")
@IdClass(PromotionManufacturerEntityPK.class)
public class PromotionManufacturerEntity {
    @Id
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Id
    @Column(name = "manufacturer_id")
    private UUID manufacturerId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", nullable = false)
    private ManufacturerEntity manufacturerByManufacturerId;
}
