package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "partner_payment_method", schema = "public", catalog = "promotion_fresher")
@IdClass(PartnerPaymentMethodEntityPK.class)
public class PartnerPaymentMethodEntity {
    @Id
    @Column(name = "partner_id")
    private UUID partnerId;

    @Id
    @Column(name = "payment_method_id")
    private UUID paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "partner_id", referencedColumnName = "partner_id", nullable = false)
    private PartnerEntity partnerByPartnerId;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethodByPaymentMethodId;
}
