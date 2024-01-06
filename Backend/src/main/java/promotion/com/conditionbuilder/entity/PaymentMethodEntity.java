package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "payment_method", schema = "public", catalog = "promotion_fresher")
public class PaymentMethodEntity {
    @Id
    @Column(name = "payment_method_id")
    private UUID id;

    @Basic
    @Column(name = "payment_method_name")
    private String name;

    @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
    private Collection<PartnerEntity> partnersByPaymentMethodId;
    @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
    private Collection<PartnerPaymentMethodEntity> partnerPaymentMethodsByPaymentMethodId;
    @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
    private Collection<PaymentEntity> paymentsByPaymentMethodId;
}
