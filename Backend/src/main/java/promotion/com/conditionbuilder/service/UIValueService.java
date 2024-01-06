package promotion.com.conditionbuilder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import promotion.com.conditionbuilder.entity.condition.ConditionEntity;
import promotion.com.conditionbuilder.entity.UI.ObjectEntity;
import promotion.com.conditionbuilder.entity.UI.PropertyTypeEntity;
import promotion.com.conditionbuilder.repository.ConditionRepository;
import promotion.com.conditionbuilder.repository.GenericRepository;
import promotion.com.conditionbuilder.repository.ObjectRepository;
import promotion.com.conditionbuilder.repository.PropertyTypeRepository;
import promotion.com.conditionbuilder.dto.ObjectDto;
import promotion.com.conditionbuilder.dto.PropertyDto;
import promotion.com.conditionbuilder.dto.PropertyTypeWithOperatorDto;
import promotion.com.conditionbuilder.dto.UiDto;
import promotion.com.conditionbuilder.dto.condition.ConditionDto;

@Service
public class UIValueService {

    private final ModelMapper modelMapper;
    private final ObjectRepository objectRepository;
    private final ConditionRepository conditionRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final GenericRepository genericRepository;

    @Autowired
    public UIValueService(ModelMapper modelMapper, ObjectRepository objectRepository, ConditionRepository conditionRepository, PropertyTypeRepository propertyTypeRepository, GenericRepository genericRepository) {
        this.modelMapper = modelMapper;
        this.objectRepository = objectRepository;
        this.conditionRepository = conditionRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.genericRepository = genericRepository;
    }

    public UiDto getAllObject() {

        List<ObjectEntity> objectEntities = objectRepository.findAll();
        List<ObjectDto> objectDtos = modelMapper.map(objectEntities, new TypeToken<List<ObjectDto>>() {
        }.getType());
        for (ObjectDto objectDto : objectDtos) {
            for (PropertyDto propertyDto : objectDto.getPropertiesByObjectId()) {
                if (!propertyDto.isPropertyNoneValue()) {
                    List<?> entityValue = setAllValueForProperty(propertyDto);
                    if (entityValue != null) {
                        propertyDto.setPropertyValuesByPropertyId(entityValue);
                    }
                }
            }
        }

        List<PropertyTypeEntity> propertyTypeEntity = propertyTypeRepository.findAll();
        List<PropertyTypeWithOperatorDto> propertyTypeWithOperatorDtos = modelMapper.map(propertyTypeEntity,
                new TypeToken<List<PropertyTypeWithOperatorDto>>() {
                }.getType());

        return new UiDto(objectDtos, propertyTypeWithOperatorDtos);
    }

    public List<?> setAllValueForProperty(PropertyDto propertyDto) {
        try {
            String entityClassName = "promotion.com.conditionbuilder.entity."
                    + propertyDto.getPropertyEntity() + "Entity";
            Class<?> entityClass = Class.forName(entityClassName);
            List<?> entityList = genericRepository.findAll((Class<?>) entityClass);

            String dtoClassName = "promotion.com.conditionbuilder.dto.ValueUiDto";
            List<Object> dtoList;
            Class<?> dtoClass = Class.forName(dtoClassName);

            dtoList = new ArrayList<>();
            for (Object entity : entityList) {
                Object dto = modelMapper.map(entity, dtoClass);
                dtoList.add(dto);
            }
            return dtoList;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
        return null;

    }

    public ConditionDto getCondiotionById(UUID id) {
        Optional<ConditionEntity> conditionEntity = conditionRepository.findById(id);
      // Xử lý trường hợp Optional rỗng tại đây
      // Ví dụ: throw exception, trả về giá trị mặc định, hoặc xử lý tùy ý
      // Ví dụ: Trả về giá trị mặc định (null) khi không tìm thấy
      return conditionEntity.<ConditionDto>map(
          entity -> modelMapper.map(entity, new TypeToken<ConditionDto>() {
          }.getType())).orElse(null);
    }

    // public List<UIDto> getUiValue() {
    // List<ObjectEntity> uiEntityList = uiRepository.findAll();
    // List<UIDto> uiDtos = modelMapper.map(uiEntityList, new
    // TypeToken<List<UIDto>>() {}.getType());
    // uiDtos.stream()
    // .collect(Collectors.groupingBy(UIDto::getObject));
    // return uiDtos;
    // }
}
