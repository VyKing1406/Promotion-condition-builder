package promotion.com.conditionbuilder.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UiDto {
    private List<ObjectDto> objectDtos;
    private List<PropertyTypeWithOperatorDto> propertyType;
}