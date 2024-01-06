package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "property_value", schema = "public", catalog = "promotion_fresher")
public class PropertyValueEntity {
    @Id
    @Column(name = "property_value_id")
    private UUID id;
    @Basic
    @Column(name = "property_value")
    private String name;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "property_id")
    private PropertyEntity propertyByPropertyId;
}
