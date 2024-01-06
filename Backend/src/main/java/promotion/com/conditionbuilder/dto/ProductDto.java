package promotion.com.conditionbuilder.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
  private UUID id;
  private String name;
  private BigInteger price;
  private String manufacturer_name;
  private String categories;
  private String business_status;
  private String product_status;
  private String color;
  private Timestamp expiry_date;
  private Timestamp manufacturing_date;
  private BigInteger quantity;
}
