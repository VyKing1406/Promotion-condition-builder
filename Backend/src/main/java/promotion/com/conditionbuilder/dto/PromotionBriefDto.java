package promotion.com.conditionbuilder.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import promotion.com.conditionbuilder.dto.condition.ConditionDto;

@Data
public class PromotionBriefDto {
  private UUID promotionId;
  private String name;
  private String description;
  private BigInteger discountValue;
  private String discountValueType;
  private String startTime;
  private String endTime;
  private String createTime;
  private BigDecimal minimumPrice;
  private DiscountTypeDto discountType;
  private PromotionTypeDto promotionType;
} 
