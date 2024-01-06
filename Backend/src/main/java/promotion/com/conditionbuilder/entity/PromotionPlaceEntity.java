package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "promotion_place", schema = "public", catalog = "promotion_fresher")
@IdClass(PromotionPlaceEntityPK.class)
public class PromotionPlaceEntity {
    @Id
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Id
    @Column(name = "place_id")
    private UUID placeId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id", nullable = false)
    private PlaceEntity placeByPlaceId;
}
