package promotion.com.conditionbuilder.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import promotion.com.conditionbuilder.dto.CartDto;
import promotion.com.conditionbuilder.dto.CartItemDto;
import promotion.com.conditionbuilder.dto.CartProductDto;
import promotion.com.conditionbuilder.dto.CartTotalDto;
import promotion.com.conditionbuilder.dto.OrderCartDto;
import promotion.com.conditionbuilder.dto.OrderContainerDto;
import promotion.com.conditionbuilder.dto.PaymentDto;
import promotion.com.conditionbuilder.dto.PeopleDto;
import promotion.com.conditionbuilder.dto.PlaceDto;
import promotion.com.conditionbuilder.dto.ProductDto;
import promotion.com.conditionbuilder.dto.PromotionDto;
import promotion.com.conditionbuilder.dto.SalePromotion;
import promotion.com.conditionbuilder.dto.TimeDto;
import promotion.com.conditionbuilder.entity.PartnerEntity;
import promotion.com.conditionbuilder.entity.PaymentEntity;
import promotion.com.conditionbuilder.entity.PeopleEntity;
import promotion.com.conditionbuilder.entity.PlaceEntity;
import promotion.com.conditionbuilder.entity.ProductEntity;
import promotion.com.conditionbuilder.repository.EventRepository;
import promotion.com.conditionbuilder.repository.PartnerRepository;
import promotion.com.conditionbuilder.repository.PaymentRepository;
import promotion.com.conditionbuilder.repository.PeopleRepository;
import promotion.com.conditionbuilder.repository.PlaceRepository;
import promotion.com.conditionbuilder.repository.ProductRepository;
import promotion.com.conditionbuilder.service.interfaceService.EnrichDataInterface;

@Service
public class EnrichDataService implements EnrichDataInterface {

  private final PaymentRepository paymentRepository;
  private final PeopleRepository peopleRepository;
  private final PlaceRepository placeRepository;
  private final ProductRepository productRepository;
  private final EventRepository eventRepository;
  private final PartnerRepository partnerRepository;
  private final ConditionBuilderService conditionBuilderService;

  public EnrichDataService(PaymentRepository paymentRepository, ConditionBuilderService conditionBuilderService, PeopleRepository peopleRepository, PlaceRepository placeRepository, ProductRepository productRepository, EventRepository eventRepository, PartnerRepository partnerRepository) {
    this.paymentRepository = paymentRepository;
    this.peopleRepository = peopleRepository;
    this.placeRepository = placeRepository;
    this.productRepository = productRepository;
    this.eventRepository = eventRepository;
    this.partnerRepository = partnerRepository;
    this.conditionBuilderService = conditionBuilderService;
  }

  public OrderCartDto enrichCart(CartDto cartDto) {
    List<ProductDto> productList = new ArrayList<>();
    List<CartItemDto> cartItemDtoList = new ArrayList<>();

    for (CartProductDto products:cartDto.getProducts()) {
      if(products.getProductId() != null){
        ProductDto product = enrichProduct(products.getProductId());

        if(product != null){
          product.setQuantity(products.getQuantity());
          productList.add(product);
          CartItemDto cartItemDto = new CartItemDto();
          cartItemDto.setProductDto(product);
          cartItemDto.setQuantity(products.getQuantity());
          cartItemDtoList.add(cartItemDto);
        }
      }
    }

    return OrderCartDto.builder()
        .cart(CartTotalDto.builder()
            .total_amount(totalAmount(cartItemDtoList))
            .build())
        .time(enrichTime())
        .product(productList)
        .people(enrichPeople(cartDto.getPeople_id()))
        .place(enrichPlace(cartDto.getPlace_id()))
        .payment(enrichPayment(cartDto.getPayment_id(),cartDto.getPartner_id()))
        .build();
  }

  public ProductDto enrichProduct(UUID product_id) {
    ProductEntity product;
    ProductDto productDto = null;

    if(product_id != null){
      product = productRepository.findById(product_id).orElse(null);

      if(product != null){
        productDto = ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .manufacturing_date(product.getManufacturingDate())
            .manufacturer_name(product.getManufacturerByManufacturerId().getName())
            .categories(product.getCategoryByCategoryId().getName())
            .business_status(product.getBusinessStatusByBusinessStatusId().getName())
            .product_status(product.getProductStatusByProductStatusId().getProductStatusName())
            .color(product.getColorByColorId().getName())
            .expiry_date(product.getExpiryDate())
            .manufacturing_date(product.getManufacturingDate())
            .build();
      }
    }
    return productDto;
  }

  public PeopleDto enrichPeople(UUID people_id) {
    PeopleEntity people;
    PeopleDto peopleDto = null;

    if(people_id != null){
      people = peopleRepository.findById(people_id).orElse(null);

      if(people != null){
        peopleDto = PeopleDto.builder()
            .full_name(people.getFullName())
            .people_id(people.getPeopleId())
            .phone_number(people.getPhone())
            .birth(people.getBirth())
            .age_group(people.getAgeGroupByAgeGroupId().getName())
            .job(people.getJobByJobId().getName())
            .rank(people.getRankByRankId().getName())
            .first_time(people.getFirstTime())
            .build();
      }
    }
    return peopleDto;
  }

  public PaymentDto enrichPayment(UUID payment_id, UUID partner_id) {
    PartnerEntity partner = null;
    if(partner_id != null){
      partner = partnerRepository.findById(partner_id).orElse(null);
    }

    PaymentEntity payment;
    PaymentDto paymentDto = null;
    if (payment_id != null) {
      payment = paymentRepository.findById(payment_id).orElse(null);

      if(payment != null){
        PaymentDto.PaymentDtoBuilder paymentDtoBuilder = PaymentDto.builder()
            .payment_method(payment.getPaymentMethodByPaymentMethodId().getName())
            .payment_type(payment.getPaymentTypeByPaymentTypeId().getName())
            .prepay_amount(payment.getPaymentTypeByPaymentTypeId().getPrepayAmount())
            .period(payment.getPaymentTypeByPaymentTypeId().getPeriod());

        if (partner != null) {
          paymentDtoBuilder.installment_partner(partner.getPartnerName());
        }
        paymentDto = paymentDtoBuilder.build();
      }
    }
    return paymentDto;
  }

  public PlaceDto enrichPlace(UUID place_id) {
    PlaceEntity place;
    PlaceDto placeDto = null;
    if(place_id != null){
      place = placeRepository.findById(place_id).orElse(null);

      if(place != null){
        placeDto = PlaceDto.builder()
            .store_name(place.getStoreByStoreId().getBrandByBrandId().getName())
            .region_name(place.getStoreByStoreId().getProvinceByProvinceId().getRegionByRegionId().getName())
            .province_name(place.getStoreByStoreId().getProvinceByProvinceId().getProvinceName())
            .brand(place.getStoreByStoreId().getBrandByBrandId().getName())
            .size_type(place.getStoreByStoreId().getSizeBySizeId().getName())
            .channel_name(place.getChannelByChannelId().getPlatformByPlatformId().getPlatformName())
            .build();
      }
    }
    return placeDto;
  }

  public TimeDto enrichTime() {
    LocalDateTime currentDate = LocalDateTime.now();

    return TimeDto.builder()
        .current(OffsetDateTime.now())
        .day_in_week(LocalDateTime.now().getDayOfWeek().getValue())
        .event_name(eventRepository.getEventName(currentDate))
        .event_type(eventRepository.getEventType(currentDate))
        .day(LocalDateTime.now().getDayOfMonth())
        .month(LocalDateTime.now().getMonthValue())
        .year(LocalDateTime.now().getYear())
        .build();
  }

  public List<PromotionDto> enrichPromotion(OrderContainerDto orderContainerDto) {
    List<PromotionDto> promotionDtos = new ArrayList<>();
    for (SalePromotion salePromotion: orderContainerDto.getSale_promotions()) {
      if(salePromotion != null){
        UUID promotion_id = UUID.fromString(salePromotion.getPromotion_id());
        PromotionDto promotionDto = conditionBuilderService.getConditionStatement(promotion_id);
        if(promotionDto != null){
          promotionDtos.add(promotionDto);
        }
      }
    }
    return promotionDtos;
  }

  private BigInteger totalAmount(List<CartItemDto> totalAmountDtos) {
    BigInteger totalAmount = BigInteger.ZERO;

    for (CartItemDto totalAmountDto : totalAmountDtos) {
      BigInteger price = new BigInteger(String.valueOf(totalAmountDto.getProductDto().getPrice()));
      BigInteger quantity = new BigInteger(String.valueOf(totalAmountDto.getQuantity()));
      BigInteger total = price.multiply(quantity);
      totalAmount = totalAmount.add(total);
    }

    return totalAmount;
  }
}