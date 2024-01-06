package promotion.com.conditionbuilder.dto.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class ConditionDto {
  private UUID id;
  private String type;
  private UUID propertyId;
  private String property;
  private String property_type;
  private String propertyName;
  private UUID objectId;
  private String object;
  private String objectName;
  private UUID operatorId;
  private String operator;
  private String operatorName;
  private String propertyLabel;
  private String key;
  private String objectLabel;
  private boolean propertyNoneValue;
  private ConditionPropertyTypeDto propertyTypeByPropertyTypeId;
  private List<ConditionValueDto> value;
  private List<ConditionDto> children = new ArrayList<>();
}
