package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "store", schema = "public", catalog = "promotion_fresher")
public class StoreEntity {
    @Id
    @Column(name = "store_id")
    private UUID storeId;

    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<PlaceEntity> placesByStoreId;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", nullable = false)
    private BrandEntity brandByBrandId;
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "size_id", nullable = false)
    private SizeEntity sizeBySizeId;
    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id", nullable = false)
    private ProvinceEntity provinceByProvinceId;
}
