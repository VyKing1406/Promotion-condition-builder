package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "cart_payment", schema = "public", catalog = "promotion_fresher")
@IdClass(CartPaymentEntityPK.class)
public class CartPaymentEntity {
    @Id
    @Column(name = "cart_id")
    private UUID cartId;
    @Id
    @Column(name = "payment_id")
    private UUID paymentId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false)
    private CartEntity cartByCartId;
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false)
    private PaymentEntity paymentByPaymentId;
}
