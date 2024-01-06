package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "manufacturer", schema = "public", catalog = "promotion_fresher")
public class ManufacturerEntity {
    @Id
    @Column(name = "manufacturer_id")
    private UUID id;

    @Basic
    @Column(name = "manufacturer_name")
    private String name;

    @OneToMany(mappedBy = "manufacturerByManufacturerId")
    private Collection<ProductEntity> productsByManufacturerId;
    @OneToMany(mappedBy = "manufacturerByManufacturerId")
    private Collection<PromotionManufacturerEntity> promotionManufacturersByManufacturerId;
}
