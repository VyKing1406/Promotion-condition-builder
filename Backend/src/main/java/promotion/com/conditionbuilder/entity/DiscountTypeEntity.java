package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "discount_type", schema = "public", catalog = "promotion_fresher")
public class DiscountTypeEntity {
    @Id
    @Column(name = "discount_type_id")
    private UUID discountTypeId;

    @Basic
    @Column(name = "discount_type_name")
    private String discountTypeName;

    @OneToMany(mappedBy = "discountValue")
    private Collection<PromotionEntity> promotionsByDiscountTypeId;
}
