package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "category", schema = "public", catalog = "promotion_fresher")
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    private UUID id;

    @Basic
    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "categoryByCategoryId")
    private Collection<ProductEntity> productsByCategoryId;
    @OneToMany(mappedBy = "categoryByCategoryId")
    private Collection<PromotionCategoryEntity> promotionCategoriesByCategoryId;
}
