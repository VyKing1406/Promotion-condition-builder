package promotion.com.conditionbuilder.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import promotion.com.conditionbuilder.dto.condition.ConditionDto;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDto {
  private UUID promotionId;
  private String name;
  private String description;
  private BigInteger discountValue;
  private String startTime;
  private String endTime;
  private String createTime;
  private BigDecimal minTotalAmount;
  private BigDecimal maxTotalAmount;
  private ConditionDto condition;
  private DiscountTypeDto discountType;
  private PromotionTypeDto promotionType;
  private List<PromotionProductDto> promotionItemsByPromotionId;
}

