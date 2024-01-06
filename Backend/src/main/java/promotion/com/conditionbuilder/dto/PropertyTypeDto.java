package promotion.com.conditionbuilder.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeDto {
    private UUID id;
    private String name;
}
