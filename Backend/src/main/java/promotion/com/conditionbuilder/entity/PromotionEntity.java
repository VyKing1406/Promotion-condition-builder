package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;
import promotion.com.conditionbuilder.entity.condition.ConditionEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "promotion", schema = "public", catalog = "promotion_fresher")
public class PromotionEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Basic
    @Column(name = "promotion_name")
    private String name;

    @Lob
    @Column(name = "promotion_description")
    private String description;


    @Basic
    @Column(name = "discount_value")
    private BigInteger discountValue;


    @Basic
    @Column(name = "promotion_start_time")
    private String startTime;

    @Basic
    @Column(name = "promotion_end_time")
    private String endTime;

    @Basic
    @Column(name = "promotion_create_time")
    private String createTime;

    @Basic
    @Column(name = "min_total_amount")
    private BigDecimal minTotalAmount;

    @Basic
    @Column(name = "max_total_amount")
    private BigDecimal maxTotalAmount;

    @OneToOne
    @JoinColumn(name = "condition_root_id")
    private ConditionEntity condition;

    @ManyToOne
    @JoinColumn(name = "discount_type_id", referencedColumnName = "discount_type_id")
    private DiscountTypeEntity discountType;
    @ManyToOne
    @JoinColumn(name = "promotion_type_id", referencedColumnName = "promotion_type_id")
    private PromotionTypeEntity promotionType;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionCategoryEntity> promotionCategoriesByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionEventEntity> promotionEventsByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionProductEntity> promotionItemsByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionManufacturerEntity> promotionManufacturersByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionPaymentEntity> promotionPaymentsByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionPeopleEntity> promotionPeopleByPromotionId;
    @OneToMany(mappedBy = "promotionByPromotionId")
    private Collection<PromotionPlaceEntity> promotionPlacesByPromotionId;
}