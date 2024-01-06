package promotion.com.conditionbuilder.service.interfaceService;

import java.util.List;
import java.util.UUID;
import promotion.com.conditionbuilder.dto.CartDto;
import promotion.com.conditionbuilder.dto.OrderCartDto;
import promotion.com.conditionbuilder.dto.OrderContainerDto;
import promotion.com.conditionbuilder.dto.PaymentDto;
import promotion.com.conditionbuilder.dto.PeopleDto;
import promotion.com.conditionbuilder.dto.PlaceDto;
import promotion.com.conditionbuilder.dto.ProductDto;
import promotion.com.conditionbuilder.dto.PromotionDto;
import promotion.com.conditionbuilder.dto.TimeDto;

public interface EnrichDataInterface {

  OrderCartDto enrichCart(CartDto cartDto);

  ProductDto enrichProduct(UUID product_id);

  PeopleDto enrichPeople(UUID people_id);

  PaymentDto enrichPayment(UUID payment_id, UUID partner_id);

  PlaceDto enrichPlace(UUID place_id);

  TimeDto enrichTime();

  List<PromotionDto> enrichPromotion(OrderContainerDto orderContainerDto);
}
