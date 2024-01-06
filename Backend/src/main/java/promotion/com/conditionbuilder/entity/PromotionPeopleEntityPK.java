package promotion.com.conditionbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PromotionPeopleEntityPK implements Serializable {
    @Column(name = "promotion_id")
    @Id
    private UUID promotionId;

    @Column(name = "people_id")
    @Id
    private UUID peopleId;
}
