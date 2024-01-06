package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "product_status", schema = "public", catalog = "promotion_fresher")
public class ProductStatusEntity {
    @Id
    @Column(name = "product_status_id")
    private UUID productStatusId;

    @Basic
    @Column(name = "product_status_name")
    private String productStatusName;

    @OneToMany(mappedBy = "productStatusByProductStatusId")
    private Collection<ProductEntity> productsByProductStatusId;
}
