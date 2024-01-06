package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "property", schema = "public", catalog = "promotion_fresher")
public class PropertyEntity {
    @Id
    @Column(name = "property_id")
    private UUID id;
    @Basic
    @Column(name = "property_label")
    private String label;
    @Basic
    @Column(name = "property_name")
    private String name;
    @Basic
    @Column(name = "property_none_value")
    private boolean propertyNoneValue;
    @Basic
    @Column(name = "property_entity")
    private String propertyEntity;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "object_id")
    private ObjectEntity objectByObjectId;
    @ManyToOne
    @JoinColumn(name = "property_type_id", referencedColumnName = "property_type_id")
    private PropertyTypeEntity propertyTypeByPropertyTypeId;
    @OneToMany(mappedBy = "propertyByPropertyId")
    private Collection<PropertyValueEntity> propertyValuesByPropertyId;

}
