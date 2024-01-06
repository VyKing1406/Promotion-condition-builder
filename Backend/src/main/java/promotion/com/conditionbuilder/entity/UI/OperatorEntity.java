package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "operator", schema = "public", catalog = "promotion_fresher")
public class OperatorEntity {
    @Id
    @Column(name = "operator_id")
    private UUID id;
    @Basic
    @Column(name = "operator_label")
    private String label;
    @Basic
    @Column(name = "operator_name")
    private String name;

    @OneToMany(mappedBy = "operatorByOperatorId")
    private Collection<PropertyTypeOperatorEntity> propertyTypeOperatorsByObjectId;
}
