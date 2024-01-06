package promotion.com.conditionbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
  private CartTotalDto cart;
  private PeopleDto people;
  private ProductDto product;
  private PlaceDto place;
  private TimeDto time;
  private PaymentDto payment;
}