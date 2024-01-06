package promotion.com.conditionbuilder.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCartDto {
  private CartTotalDto cart;
  private PeopleDto people;
  private List<ProductDto> product;
  private PlaceDto place;
  private TimeDto time;
  private PaymentDto payment;
}
