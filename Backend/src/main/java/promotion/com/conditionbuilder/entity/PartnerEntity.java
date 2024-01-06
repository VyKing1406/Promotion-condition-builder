package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "partner", schema = "public", catalog = "promotion_fresher")
public class PartnerEntity {
    @Id
    @Column(name = "partner_id")
    private UUID partnerId;

    @Basic
    @Column(name = "partner_name")
    private String partnerName;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id")
    private PaymentTypeEntity paymentTypeByPaymentTypeId;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id")
    private PaymentMethodEntity paymentMethodByPaymentMethodId;
    @OneToMany(mappedBy = "partnerByPartnerId")
    private Collection<PartnerPaymentMethodEntity> partnerPaymentMethodsByPartnerId;
    @OneToMany(mappedBy = "partnerByPartnerId")
    private Collection<PartnerPaymentTypeEntity> partnerPaymentTypesByPartnerId;
}
