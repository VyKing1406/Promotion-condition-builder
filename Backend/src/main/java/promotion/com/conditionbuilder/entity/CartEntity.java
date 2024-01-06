package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
@Data
@Entity
@Table(name = "cart", schema = "public", catalog = "promotion_fresher")
public class CartEntity {
    @Basic
    @Column(name = "total_price")
    private long totalPrice;
    @Id
    @Column(name = "cart_id")
    private UUID cartId;

    @Basic
    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "people_id", nullable = false)
    private PeopleEntity peopleByPeopleId;
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id", nullable = false)
    private PlaceEntity placeByPlaceId;
    @OneToMany(mappedBy = "cartByCartId")
    private Collection<CartItemEntity> cartItemsByCartId;
    @OneToMany(mappedBy = "cartByCartId")
    private Collection<CartPaymentEntity> cartPaymentsByCartId;
}
