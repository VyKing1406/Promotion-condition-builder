package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "business_status", schema = "public", catalog = "promotion_fresher")
public class BusinessStatusEntity {
    @Id
    @Column(name = "business_status_id")
    private UUID id;

    @Basic
    @Column(name = "business_status_name")
    private String name;

    @OneToMany(mappedBy = "businessStatusByBusinessStatusId")
    private Collection<ProductEntity> productsByBusinessStatusId;
}
