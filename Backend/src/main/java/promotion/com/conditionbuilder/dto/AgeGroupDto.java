package promotion.com.conditionbuilder.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeGroupDto {
  private UUID id;
  private String name;
  private Integer startAge;
  private Integer endAge;
}
