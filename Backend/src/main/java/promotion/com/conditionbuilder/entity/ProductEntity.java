package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "product", schema = "public", catalog = "promotion_fresher")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private UUID id;

    @Basic
    @Column(name = "product_name")
    private String name;

    @Basic
    @Column(name = "price")
    private BigInteger price;

    @Basic
    @Column(name = "manufacturing_date")
    private Timestamp manufacturingDate;

    @Basic
    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @OneToMany(mappedBy = "productByProductId")
    private Collection<CartItemEntity> cartItemsByProductId;
    @ManyToOne
    @JoinColumn(name = "business_status_id", referencedColumnName = "business_status_id", nullable = false)
    private BusinessStatusEntity businessStatusByBusinessStatusId;
    @ManyToOne
    @JoinColumn(name = "product_status_id", referencedColumnName = "product_status_id", nullable = false)
    private ProductStatusEntity productStatusByProductStatusId;
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private ColorEntity colorByColorId;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", nullable = false)
    private ManufacturerEntity manufacturerByManufacturerId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private CategoryEntity categoryByCategoryId;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<PromotionProductEntity> promotionItemsByProductId;
}
