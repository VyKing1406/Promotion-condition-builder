package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "color", schema = "public", catalog = "promotion_fresher")
public class ColorEntity {
    @Id
    @Column(name = "color_id")
    private UUID id;

    @Basic
    @Column(name = "color_name")
    private String name;

    @Basic
    @Column(name = "color_hex_code")
    private String colorHexCode;

    @OneToMany(mappedBy = "colorByColorId")
    private Collection<ProductEntity> productsByColorId;
}
