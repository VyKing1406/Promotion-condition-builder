package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "province", schema = "public", catalog = "promotion_fresher")
public class ProvinceEntity {
    @Id
    @Column(name = "province_id")
    private UUID provinceId;

    @Basic
    @Column(name = "province_name")
    private String provinceName;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false)
    private RegionEntity regionByRegionId;

    @OneToMany(mappedBy = "provinceByProvinceId")
    private Collection<StoreEntity> storesByProvinceId;

    @OneToMany(mappedBy = "provinceByProvinceId")
    private Collection<AddressEntity> addressesByProvinceId;
}
