package promotion.com.conditionbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
public class PartnerPaymentMethodEntityPK implements Serializable {
    @Id
    @Column(name = "partner_id")
    private UUID partnerId;

    @Id
    @Column(name = "payment_method_id")
    private UUID paymentMethodId;
}
