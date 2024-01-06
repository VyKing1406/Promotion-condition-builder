package promotion.com.conditionbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
public class CartPaymentEntityPK implements Serializable {
    @Id
    @Column(name = "cart_id")
    private UUID cartId;
    @Id
    @Column(name = "payment_id")
    private UUID paymentId;
}
