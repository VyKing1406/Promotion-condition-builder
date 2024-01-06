package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "promotion_people", schema = "public", catalog = "promotion_fresher")
@IdClass(PromotionPeopleEntityPK.class)
public class PromotionPeopleEntity {
    @Id
    @Column(name = "promotion_id")
    private UUID promotionId;

    @Id
    @Column(name = "people_id")
    private UUID peopleId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id", nullable = false)
    private PromotionEntity promotionByPromotionId;
    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "people_id", nullable = false)
    private PeopleEntity peopleByPeopleId;
}
