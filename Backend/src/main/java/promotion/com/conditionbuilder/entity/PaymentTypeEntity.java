package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "payment_type", schema = "public", catalog = "promotion_fresher")
public class PaymentTypeEntity {
    @Id
    @Column(name = "payment_type_id")
    private UUID id;

    @Basic
    @Column(name = "payment_type_name")
    private String name;

    @Basic
    @Column(name = "period")
    private Integer period;

    @Basic
    @Column(name = "prepay_amount")
    private Integer prepayAmount;

    @Basic
    @Column(name = "interest_rate")
    private Integer interestRate;

    @OneToMany(mappedBy = "paymentTypeByPaymentTypeId")
    private Collection<PartnerEntity> partnersByPaymentTypeId;
    @OneToMany(mappedBy = "paymentTypeByPaymentTypeId")
    private Collection<PartnerPaymentTypeEntity> partnerPaymentTypesByPaymentTypeId;
    @OneToMany(mappedBy = "paymentTypeByPaymentTypeId")
    private Collection<PaymentEntity> paymentsByPaymentTypeId;
}
