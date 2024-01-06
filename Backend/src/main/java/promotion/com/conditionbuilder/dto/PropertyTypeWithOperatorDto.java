package promotion.com.conditionbuilder.dto;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeWithOperatorDto {
    private UUID id;
    private String name;
    private Collection<PropertyTypeOperatorDto> propertyTypeOperatorsByPropertyTypeId;
}