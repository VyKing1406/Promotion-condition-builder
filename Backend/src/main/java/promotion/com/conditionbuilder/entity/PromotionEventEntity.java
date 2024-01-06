package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "promotion_event", schema = "public", catalog = "promotion_fresher")
@IdClass(PromotionEventEntityPK.class)
public class PromotionEventEntity {
    @Id
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private EventEntity eventByEventId;
}
