package promotion.com.conditionbuilder.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import promotion.com.conditionbuilder.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
  @Query(value = "SELECT e.event_name FROM event e LEFT JOIN time t ON e.time_id = t.time_id WHERE :currentDate BETWEEN t.start_date AND t.end_date", nativeQuery = true)
  List<String> getEventName(@Param("currentDate") LocalDateTime currentDate);

  @Query(value = "SELECT et.event_type_name FROM event e LEFT JOIN time t ON e.time_id = t.time_id LEFT JOIN event_type et ON e.event_type_id = et.event_type_id  WHERE :currentDate BETWEEN t.start_date AND t.end_date", nativeQuery = true)
  List<String> getEventType(@Param("currentDate") LocalDateTime currentDate);
}
