package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "size", schema = "public", catalog = "promotion_fresher")
public class SizeEntity {
    @Id
    @Column(name = "size_id")
    private UUID id;

    @Basic
    @Column(name = "size_type")
    private String name;

    @OneToMany(mappedBy = "sizeBySizeId")
    private Collection<StoreEntity> storesBySizeId;
}
