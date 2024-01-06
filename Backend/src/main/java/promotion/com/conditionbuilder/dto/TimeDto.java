package promotion.com.conditionbuilder.dto;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {
  private OffsetDateTime current;
  private List<String> event_type;
  private List<String> event_name;
  private Integer day_in_week;
  private Integer day;
  private Integer month;
  private Integer year;
}
