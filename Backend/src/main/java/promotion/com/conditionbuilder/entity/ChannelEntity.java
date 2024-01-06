package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "channel", schema = "public", catalog = "promotion_fresher")
public class ChannelEntity {
    @Id
    @Column(name = "channel_id")
    private UUID id;

    @Basic
    @Column(name = "url")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", nullable = false)
    private BrandEntity brandByBrandId;
    @ManyToOne
    @JoinColumn(name = "platform_id", referencedColumnName = "platform_id", nullable = false)
    private PlatformEntity platformByPlatformId;
    @OneToMany(mappedBy = "channelByChannelId")
    private Collection<PlaceEntity> placesByChannelId;
}
