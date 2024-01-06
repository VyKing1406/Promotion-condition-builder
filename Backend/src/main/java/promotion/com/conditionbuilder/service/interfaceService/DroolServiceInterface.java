package promotion.com.conditionbuilder.service.interfaceService;

import promotion.com.conditionbuilder.dto.OrderCartDto;
import promotion.com.conditionbuilder.dto.OrderContainerDto;

public interface DroolServiceInterface {
  OrderContainerDto getPromotion(OrderCartDto orderDto);
}