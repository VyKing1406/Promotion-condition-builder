package promotion.com.conditionbuilder.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionProductDto {
    private UUID id;
    private Integer quantity;
    private Integer minQuantity;
    private Integer maxQuantity;
    private ProductDto productByProductId;
}
