package promotion.com.conditionbuilder.dto;



import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorDto {
    private UUID id;
    private String label;
    private String name;
}
