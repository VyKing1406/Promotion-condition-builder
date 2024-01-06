package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "ui", schema = "public", catalog = "promotion_fresher")
public class UiEntity {
    @Id
    @Column(name = "object_id")
    private UUID object_id;

    @Basic
    @Column(name = "object")
    private String object_name;

    @Basic
    @Column(name = "operator")
    private String operator;
//
//    @ManyToOne
//    @JoinColumn(name = "value_id", referencedColumnName = "value_id")
//    private ValueEntity valueByValueId;
}
