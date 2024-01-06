package promotion.com.conditionbuilder.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private UUID id;
    private String name;
}
