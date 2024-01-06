package promotion.com.conditionbuilder.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
  private UUID people_id;
  private List<CartProductDto> products;
  private UUID place_id;
  private UUID payment_id;
  private UUID partner_id;
}
