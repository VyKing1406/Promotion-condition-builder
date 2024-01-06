package promotion.com.conditionbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
public class PartnerPaymentTypeEntityPK implements Serializable {
    @Column(name = "partner_id")
    @Id
    private UUID partnerId;

    @Column(name = "payment_type_id")
    @Id
    private UUID paymentTypeId;
}
