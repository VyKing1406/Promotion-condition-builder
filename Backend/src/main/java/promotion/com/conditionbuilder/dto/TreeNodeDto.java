package promotion.com.conditionbuilder.dto;


import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNodeDto {
    private UUID id;
    private String value;
    private List<TreeNodeDto> childrenNode;
}