package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "place", schema = "public", catalog = "promotion_fresher")
public class PlaceEntity {
    @Id
    @Column(name = "place_id")
    private UUID placeId;

    @OneToMany(mappedBy = "placeByPlaceId")
    private Collection<CartEntity> cartsByPlaceId;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private StoreEntity storeByStoreId;
    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
    private ChannelEntity channelByChannelId;
    @OneToMany(mappedBy = "placeByPlaceId")
    private Collection<PromotionPlaceEntity> promotionPlacesByPlaceId;
}
