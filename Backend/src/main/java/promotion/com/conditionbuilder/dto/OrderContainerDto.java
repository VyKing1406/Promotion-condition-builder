package promotion.com.conditionbuilder.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderContainerDto {
  Set<SalePromotion> sale_promotions = new HashSet<SalePromotion>();
  public void addSale(SalePromotion salePromotion){
    this.sale_promotions.add(salePromotion);
  }
}
