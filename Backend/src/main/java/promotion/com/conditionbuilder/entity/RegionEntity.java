package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "region", schema = "public", catalog = "promotion_fresher")
public class RegionEntity {
    @Id
    @Column(name = "region_id")
    private UUID id;

    @Basic
    @Column(name = "region_name")
    private String name;

    @OneToMany(mappedBy = "regionByRegionId")
    private Collection<ProvinceEntity> provincesByRegionId;
}
