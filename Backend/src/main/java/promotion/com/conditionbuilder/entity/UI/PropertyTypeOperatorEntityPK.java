package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PropertyTypeOperatorEntityPK implements Serializable {
    @Column(name = "property_type_id")
    @Id
    private UUID propertyTypeId;
    @Column(name = "operator_id")
    @Id
    private UUID operatorId;
}
