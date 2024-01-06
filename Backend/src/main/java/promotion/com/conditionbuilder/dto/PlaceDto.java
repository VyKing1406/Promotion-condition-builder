package promotion.com.conditionbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDto {
  private String store_name;
  private String region_name;
  private String province_name;
  private String brand;
  private String size_type;
  private String channel_name;
}
