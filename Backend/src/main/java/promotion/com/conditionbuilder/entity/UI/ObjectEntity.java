package promotion.com.conditionbuilder.entity.UI;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "object", schema = "public", catalog = "promotion_fresher")
public class ObjectEntity {
    @Id
    @Column(name = "object_id")
    private UUID id;
    @Basic
    @Column(name = "object_label")
    private String label;
    @Basic
    @Column(name = "object_name")
    private String name;

    @OneToMany(mappedBy = "objectByObjectId")
    private Collection<PropertyEntity> propertiesByObjectId;
}
