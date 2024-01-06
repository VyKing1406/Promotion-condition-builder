package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "partner_payment_type", schema = "public", catalog = "promotion_fresher")
@IdClass(PartnerPaymentTypeEntityPK.class)
public class PartnerPaymentTypeEntity {
    @Id
    @Column(name = "partner_id")
    private UUID partnerId;

    @Id
    @Column(name = "payment_type_id")
    private UUID paymentTypeId;

    @ManyToOne
    @JoinColumn(name = "partner_id", referencedColumnName = "partner_id", nullable = false)
    private PartnerEntity partnerByPartnerId;
    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id", nullable = false)
    private PaymentTypeEntity paymentTypeByPaymentTypeId;
}
