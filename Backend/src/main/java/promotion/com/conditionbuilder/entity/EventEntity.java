package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "event", schema = "public", catalog = "promotion_fresher")
public class EventEntity {
    @Id
    @Column(name = "event_id")
    private UUID id;

    @Basic
    @Column(name = "event_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "event_type_id", referencedColumnName = "event_type_id", nullable = false)
    private EventTypeEntity eventTypeByEventTypeId;
    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "time_id", nullable = false)
    private TimeEntity timeByTimeId;
    @OneToMany(mappedBy = "eventByEventId")
    private Collection<PromotionEventEntity> promotionEventsByEventId;
}
