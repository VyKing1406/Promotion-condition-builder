package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "brand", schema = "public", catalog = "promotion_fresher")
public class BrandEntity {
    @Id
    @Column(name = "brand_id")
    private UUID id;

    @Basic
    @Column(name = "brand_name")
    private String name;

    @OneToMany(mappedBy = "brandByBrandId")
    private Collection<ChannelEntity> channelsByBrandId;
    @OneToMany(mappedBy = "brandByBrandId")
    private Collection<StoreEntity> storesByBrandId;
}
