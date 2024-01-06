package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "event_type", schema = "public", catalog = "promotion_fresher")
public class EventTypeEntity {
    @Id
    @Column(name = "event_type_id")
    private UUID eventTypeId;

    @Basic
    @Column(name = "event_type_name")
    private String eventTypeName;

    @OneToMany(mappedBy = "eventTypeByEventTypeId")
    private Collection<EventEntity> eventsByEventTypeId;
}
