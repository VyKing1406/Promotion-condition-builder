package promotion.com.conditionbuilder.entity.condition;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "condition_value", schema = "public", catalog = "promotion_fresher")
public class ConditionValueEntity {
    @Id
    @Column(name = "condition_value_id")
    private UUID id;

    @Column(name = "value_id")
    private UUID valueId;

    @Basic
    @Column(name = "condition_value")
    private String valueName;

    @ManyToOne
    @JoinColumn(name = "condition_id", referencedColumnName = "id")
    private ConditionEntity condition;
}