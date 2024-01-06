package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "property_type_operator", schema = "public", catalog = "promotion_fresher")
@IdClass(PropertyTypeOperatorEntityPK.class)
public class PropertyTypeOperatorEntity {
    @Id
    @Column(name = "property_type_id")
    private UUID propertyTypeId;
    @Id
    @Column(name = "operator_id")
    private UUID operatorId;

    @ManyToOne
    @JoinColumn(name = "property_type_id", referencedColumnName = "property_type_id", nullable = false)
    private PropertyTypeEntity propertyTypeByPropertyTypeId;
    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "operator_id", nullable = false)
    private OperatorEntity operatorByOperatorId;
}
