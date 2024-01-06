package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "promotion_payment", schema = "public", catalog = "promotion_fresher")
@IdClass(PromotionPaymentEntityPK.class)
public class PromotionPaymentEntity {
    @Id
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Id
    @Column(name = "payment_id")
    private UUID paymentId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false)
    private PaymentEntity paymentByPaymentId;
}
