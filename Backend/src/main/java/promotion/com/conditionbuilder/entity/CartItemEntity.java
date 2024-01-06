package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "cart_item", schema = "public", catalog = "promotion_fresher")
public class CartItemEntity {
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @Column(name = "cart_item_id")
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private ProductEntity productByProductId;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false)
    private CartEntity cartByCartId;
}
