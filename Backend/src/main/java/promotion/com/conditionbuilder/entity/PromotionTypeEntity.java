package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "promotion_type", schema = "public", catalog = "promotion_fresher")
public class PromotionTypeEntity {
    @Id
    @Column(name = "promotion_type_id")
    private UUID promotionTypeId;

    @Basic
    @Column(name = "promotion_type_name")
    private String promotionTypeName;
    @OneToMany(mappedBy = "promotionType")
    private Collection<PromotionEntity> promotionsByPromotionTypeId;
}
