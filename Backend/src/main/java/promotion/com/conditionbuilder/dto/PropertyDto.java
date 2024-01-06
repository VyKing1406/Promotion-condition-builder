package promotion.com.conditionbuilder.dto;


import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    private UUID id;
    private String label;
    private String name;
    private String propertyEntity;
    private boolean propertyNoneValue;
    private PropertyTypeDto propertyTypeByPropertyTypeId;
    private Collection<?> propertyValuesByPropertyId;
}


