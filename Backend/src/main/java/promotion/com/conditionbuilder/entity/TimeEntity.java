package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "time", schema = "public", catalog = "promotion_fresher")
public class TimeEntity {
    @Id
    @Column(name = "time_id")
    private UUID timeId;

    @Basic
    @Column(name = "start_date")
    private Timestamp startDate;

    @Basic
    @Column(name = "end_date")
    private Timestamp endDate;

    @OneToMany(mappedBy = "timeByTimeId")
    private Collection<EventEntity> eventsByTimeId;
    @Basic
    @Column(name = "day_in_week")
    private String dayInWeek;
}
