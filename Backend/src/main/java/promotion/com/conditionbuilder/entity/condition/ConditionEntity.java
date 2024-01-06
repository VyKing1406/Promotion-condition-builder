package promotion.com.conditionbuilder.entity.condition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import promotion.com.conditionbuilder.entity.PromotionEntity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "condition", schema = "public", catalog = "promotion_fresher")
public class ConditionEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "property_id")
    private UUID propertyId;

    @Basic
    @Column(name = "condition_property_name")
    private String propertyName;

    @Basic
    @Column(name = "key")
    private String key;

    @Basic
    @Column(name = "condition_property_type")
    private String property_type;

    @Basic
    @Column(name = "condition_object_name")
    private String objectName;

    @Basic
    @Column(name = "object_id")
    private UUID objectId;

    @Basic
    @Column(name = "operator_id")
    private UUID operatorId;

    @Basic
    @Column(name = "operator_name")
    private String operatorName;

    @Basic
    @Column(name = "property_none_value")
    private boolean propertyNoneValue;

    @OneToOne(mappedBy = "condition")
    private PromotionEntity promotion;

    @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ConditionValueEntity> value;
    
    @ManyToOne
    @JoinColumn(name = "condition_parrent_id")
    private ConditionEntity parrentCondition;

    @OneToMany(mappedBy = "parrentCondition", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ConditionEntity> children;
}
