package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "payment", schema = "public", catalog = "promotion_fresher")
public class PaymentEntity {
    @Id
    @Column(name = "payment_id")
    private UUID paymentId;

    @OneToMany(mappedBy = "paymentByPaymentId")
    private Collection<CartPaymentEntity> cartPaymentsByPaymentId;
    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id", nullable = false)
    private PaymentTypeEntity paymentTypeByPaymentTypeId;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethodByPaymentMethodId;
    @OneToMany(mappedBy = "paymentByPaymentId")
    private Collection<PromotionPaymentEntity> promotionPaymentsByPaymentId;
}
