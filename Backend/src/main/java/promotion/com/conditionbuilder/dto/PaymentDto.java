package promotion.com.conditionbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
  private String payment_method;
  private String payment_type;
  private String installment_partner;
  private Integer prepay_amount;
  private Integer period;
}
