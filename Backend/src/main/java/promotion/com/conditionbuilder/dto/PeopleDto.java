package promotion.com.conditionbuilder.dto;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDto {
  private String full_name;
  private UUID people_id;
  private String phone_number;
  private Date birth;
  private String age_group;
  private String job;
  private String rank;
  private Boolean first_time;
}
