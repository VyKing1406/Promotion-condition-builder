package promotion.com.conditionbuilder.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import promotion.com.conditionbuilder.dto.OrderCartDto;
import promotion.com.conditionbuilder.dto.OrderContainerDto;
import promotion.com.conditionbuilder.dto.OrderDto;
import promotion.com.conditionbuilder.dto.ProductDto;
import promotion.com.conditionbuilder.service.interfaceService.DroolServiceInterface;

@Service
public class DroolService implements DroolServiceInterface {
  private final KieContainer kieContainer;
  public DroolService(KieContainer kieContainer){
    this.kieContainer = kieContainer;
  }

  public OrderContainerDto getPromotion(OrderCartDto orderCartDto) {
    KieSession kieSession = kieContainer.newKieSession();
    OrderContainerDto validPromotions = new OrderContainerDto();
    kieSession.setGlobal("promotionList", validPromotions);
    OrderDto orderDto = convertToOrderDto(orderCartDto);
    kieSession.insert(orderDto);

    for (ProductDto productDto: orderCartDto.getProduct()){
      kieSession.insert(productDto);
    }

    kieSession.fireAllRules();
    kieSession.dispose();
    return validPromotions;
  }

  private OrderDto convertToOrderDto(OrderCartDto orderCartDto) {
    OrderDto orderDto = new OrderDto();
    orderDto.setCart(orderCartDto.getCart());
    orderDto.setPeople(orderCartDto.getPeople());
    orderDto.setPlace(orderCartDto.getPlace());
    orderDto.setTime(orderCartDto.getTime());
    orderDto.setPayment(orderCartDto.getPayment());
    return orderDto;
  }
}
