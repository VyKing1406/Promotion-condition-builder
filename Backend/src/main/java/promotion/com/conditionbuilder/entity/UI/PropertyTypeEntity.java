package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "property_type", schema = "public", catalog = "promotion_fresher")
public class PropertyTypeEntity {
    @Id
    @Column(name = "property_type_id")
    private UUID id;
    @Basic
    @Column(name = "property_type_value")
    private String name;

    @OneToMany(mappedBy = "propertyTypeByPropertyTypeId")
    private Collection<PropertyEntity> propertiesByPropertyTypeId;
    @OneToMany(mappedBy = "propertyTypeByPropertyTypeId")
    private Collection<PropertyTypeOperatorEntity> propertyTypeOperatorsByPropertyTypeId;
}
 