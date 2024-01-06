package promotion.com.conditionbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PromotionPaymentEntityPK implements Serializable {
    @Column(name = "promotion_id")
    @Id
    private UUID promotionId;

    @Column(name = "payment_id")
    @Id
    private UUID paymentId;
}
