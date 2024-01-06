package promotion.com.conditionbuilder.service;

import jakarta.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import promotion.com.conditionbuilder.dto.PromotionBriefDto;
import promotion.com.conditionbuilder.dto.PromotionDto;
import promotion.com.conditionbuilder.dto.SalePromotion;
import promotion.com.conditionbuilder.dto.condition.ConditionDto;
import promotion.com.conditionbuilder.dto.condition.ConditionValueDto;
import promotion.com.conditionbuilder.entity.ProductEntity;
import promotion.com.conditionbuilder.entity.PromotionEntity;
import promotion.com.conditionbuilder.entity.PromotionProductEntity;
import promotion.com.conditionbuilder.entity.UI.ObjectEntity;
import promotion.com.conditionbuilder.entity.UI.PropertyEntity;
import promotion.com.conditionbuilder.entity.condition.ConditionEntity;
import promotion.com.conditionbuilder.repository.ConditionRepository;
import promotion.com.conditionbuilder.repository.GenericRepository;
import promotion.com.conditionbuilder.repository.OperatorRepository;
import promotion.com.conditionbuilder.repository.PromotionProductRepository;
import promotion.com.conditionbuilder.repository.PromotionRepository;

@Service
public class ConditionBuilderService {

  private static ModelMapper modelMapper;
  private static PromotionRepository promotionRepository;
  private final ConditionRepository conditionRepository;
  private final GenericRepository genericRepository;
  private final PromotionProductRepository promotionProductRepository;
  private final OperatorRepository operatorRepository;

  @Autowired
  public ConditionBuilderService(ModelMapper modelMapper, PromotionRepository promotionRepository,
      ConditionRepository conditionRepository,
      PromotionProductRepository promotionProductRepository,
      GenericRepository genericRepository, OperatorRepository operatorRepository) {
    ConditionBuilderService.modelMapper = modelMapper;
    ConditionBuilderService.promotionRepository = promotionRepository;
    this.conditionRepository = conditionRepository;
    this.promotionProductRepository = promotionProductRepository;
    this.genericRepository = genericRepository;
    this.operatorRepository = operatorRepository;
  }

  public String generateDrlContent(String name, ConditionDto condition, UUID promotion_id,
      String promotion_type) {
    return generateImportStatement() + generateRuleName(name) + "\twhen\n"
        + "\t\t$order: OrderDto()\n"
        + "\t\t$product: ProductDto()\n"
        + "\n\t\teval(" + buildExpression(condition)
        + ")\n"
        + createThenDrl(name, promotion_id, promotion_type);
  }

  public static String buildExpression(ConditionDto condition) {
    if (condition.getType().equals("logic")) {
      return "("
          + buildExpression(condition.getChildren().get(0))
          + "\n\t\t"
          + condition.getOperatorName()
          + " "
          + buildExpression(condition.getChildren().get(1))
          + ")";
    } else if (condition.getType().equals("condition")) {
      String operator = condition.getOperatorName();
      String dto = condition.getObjectName();
      String property = condition.getPropertyName();
      StringBuilder values = new StringBuilder();
      int count = 0;

      for (ConditionValueDto value : condition.getValue()) {
        if (condition.getProperty_type().equals("string") || condition.getProperty_type()
            .equals("list string")
            || condition.getProperty_type().equals("date")) {
          values.append("\"");
        }
        values.append(value.getValueName());
        count++;
        if (condition.getProperty_type().equals("string") || condition.getProperty_type()
            .equals("list string")
            || condition.getProperty_type().equals("date")) {
          values.append("\"");
        }
        if (count == condition.getValue().size()) {
          break;
        }
        values.append(",");
      }

      if (dto.equals("product")) {
        return "OperatorCustom." + operator + "(" + "$" + dto + "." + property + "," + "[" + values
            + "]"
            + ")";
      }

      return "OperatorCustom." + operator + "(" + "$order." + dto + "." + property + "," + "["
          + values + "]"
          + ")";
    }
    return "";
  }

  public String generateImportStatement() {
    return """
        package rules;
        dialect  "mvel"
        import promotion.com.conditionbuilder.dto.OrderDto
        global promotion.com.conditionbuilder.dto.OrderContainerDto promotionList;
        import promotion.com.conditionbuilder.dto.SalePromotion;
        import promotion.com.conditionbuilder.operatorCustom.OperatorCustom;
        import promotion.com.conditionbuilder.dto.ProductDto
        import promotion.com.conditionbuilder.dto.OrderCartDto;

        """;
  }

  public String generateRuleName(String ruleName) {
    return "rule " + "\"" + ruleName + "\"\n";
  }

  public String createThenDrl(String name, UUID promotion_id, String promotion_type) {
    return """

          then
            SalePromotion salePromotion = new SalePromotion();
            salePromotion.setPromotion_id("%s");
            salePromotion.setPromotion_type("%s");
            promotionList.addSale(salePromotion);
        end
        """.formatted(promotion_id, promotion_type);
  }

  public void createDrlFile(String name, ConditionDto condition, UUID promotion_id,
      String promotion_type)
      throws IOException {
    String drlContent = generateDrlContent(name, condition, promotion_id, promotion_type);
    BufferedWriter writer = new BufferedWriter(
        new FileWriter("./src/main/resources/rules/" + name + ".drl"));
    writer.write(drlContent);
    writer.close();
  }

  public UUID savePromotion(PromotionDto promotionDto) {
    treversalTreeSave(promotionDto.getCondition());
    PromotionEntity promotionEntity = modelMapper.map(promotionDto,
        new TypeToken<PromotionEntity>() {
        }.getType());
    promotionEntity.setPromotionId(UUID.randomUUID());
    promotionEntity.setCreateTime(LocalDateTime.now().toString());
    promotionEntity.setMinTotalAmount(BigDecimal.valueOf(0));
    ConditionEntity conditionEntity = promotionEntity.getCondition();
    conditionEntity.setId(UUID.randomUUID());
    conditionEntity.setPromotion(promotionEntity);
    promotionEntity.setCondition(conditionEntity);
    setParentChildRelationship(conditionEntity);
    conditionRepository.save(conditionEntity);
    promotionEntity.setCondition(conditionEntity);

    setTotalCart(conditionEntity, promotionEntity);

    promotionEntity = promotionRepository.save(promotionEntity);

    PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
    promotionProductEntity.setId(UUID.randomUUID());
    promotionProductEntity.setPromotionByPromotionId(promotionEntity);
    setProductItem(conditionEntity, promotionProductEntity);

    if (promotionProductEntity.getQuantity() != null
        || promotionProductEntity.getMinQuantity() != null
        || promotionProductEntity.getMaxQuantity() != null) {
      promotionProductRepository.save(promotionProductEntity);
    }

    return promotionEntity.getPromotionId();
  }

  public void treversalTreeSave(ConditionDto conditionDto) {
    if (conditionDto != null) {
      if (conditionDto.getType().equals("logic")) {
        conditionDto.setOperatorName(conditionDto.getOperator());
      } else if (conditionDto.getType().equals("condition")) {
        conditionDto.setOperatorId(UUID.fromString(conditionDto.getOperator()));
      }
      for (ConditionDto child : conditionDto.getChildren()) {
        treversalTreeSave(child);
      }
    }
  }

  public void treversalTreeGet(ConditionDto conditionDto) {
    if (conditionDto != null) {
      if (conditionDto.getType().equals("logic")) {
        conditionDto.setOperator(conditionDto.getOperatorName());
      } else if (conditionDto.getType().equals("condition")) {
        conditionDto.setOperator(conditionDto.getOperatorId().toString());
      }
      for (ConditionDto child : conditionDto.getChildren()) {
        treversalTreeGet(child);
      }
    }
  }

  @Transactional
  public UUID updatePromotion(PromotionDto promotionDto, UUID id) {
    Boolean isExist = promotionRepository.existsById(id);
    if (isExist) {
      treversalTreeSave(promotionDto.getCondition());
      PromotionEntity promotionEntity = modelMapper.map(promotionDto,
          new TypeToken<PromotionEntity>() {
          }.getType());
      if (promotionDto.getCondition() != null) {
        ConditionEntity conditionEntity = promotionEntity.getCondition();
        conditionEntity.setPromotion(promotionEntity);
        promotionEntity.setCondition(conditionEntity);
        setParentChildRelationship(conditionEntity);
        conditionEntity = conditionRepository.save(conditionEntity);
        promotionEntity.setCondition(conditionEntity);
        setTotalCart(conditionEntity, promotionEntity);
        PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
        promotionProductEntity.setPromotionByPromotionId(promotionEntity);
        setProductItem(conditionEntity, promotionProductEntity);
        if ((promotionProductEntity.getQuantity() != null
            || promotionProductEntity.getMinQuantity() != null
            || promotionProductEntity.getMaxQuantity() != null)
            && promotionProductEntity != null) {
          promotionProductEntity.setId(UUID.randomUUID());
          promotionProductRepository.save(promotionProductEntity);
        }
      }
      promotionEntity = promotionRepository.save(promotionEntity);
      return promotionEntity.getPromotionId();
    }
    return null;
  }

  public void setParentChildRelationship(ConditionEntity conditionEntity) {
    if (!conditionEntity.getChildren().isEmpty()) {
      for (ConditionEntity child : conditionEntity.getChildren()) {
        child.setParrentCondition(conditionEntity);
        child.setId(UUID.randomUUID());
        if (child.getValue() != null) {
          for (int i = 0; i < child.getValue().size(); i++) {
            child.getValue().get(i).setCondition(child);
            child.getValue().get(i).setId(UUID.randomUUID());
          }
        }
        setParentChildRelationship(child);
      }
    }
  }

  public void setTotalCart(ConditionEntity conditionEntity, PromotionEntity promotionEntity) {
    if (!conditionEntity.getChildren().isEmpty()) {
      for (ConditionEntity child : conditionEntity.getChildren()) {
        if (child.getType().equals("condition")) {
          if (child.getObjectName().equals("Cart") && child.getOperatorName().equals("between")
              && child.getPropertyName().equals("total_amount")) {
            promotionEntity
                .setMinTotalAmount(
                    BigDecimal.valueOf(Integer.valueOf(child.getValue().get(0).getValueName())));
            promotionEntity
                .setMaxTotalAmount(
                    BigDecimal.valueOf(Integer.valueOf(child.getValue().get(1).getValueName())));
          }
        }
      }
    }
  }

  public void setProductItem(ConditionEntity conditionEntity,
      PromotionProductEntity promotionProductEntity) {
    ProductEntity productEntity = new ProductEntity();
    if (!conditionEntity.getChildren().isEmpty()) {
      for (ConditionEntity child : conditionEntity.getChildren()) {
        if (child.getType().equals("condition")) {
          if (child.getObjectName().equals("product") && child.getOperatorName().equals("is")
              && child.getPropertyName().equals("id")) {
            ProductEntity product = genericRepository.findById(ProductEntity.class,
                child.getValue().get(0).getValueName());
            promotionProductEntity.setProductByProductId(product);
          }

          if (child.getObjectName().equals("product") && child.getOperatorName().equals("is")
              && child.getPropertyName().equals("quantity")) {
            promotionProductEntity.setQuantity(Integer.valueOf(child.getValue().get(0).getValueName()));
            promotionProductEntity.setProductByProductId(productEntity);
          }

          if (child.getObjectName().equals("product") && child.getPropertyName()
              .equals("name")) {
            productEntity.setId(child.getValue().get(0).getValueId());
          }

          if (child.getObjectName().equals("product") && child.getOperatorName().equals("between")
              && child.getPropertyName().equals("quantity")) {
            promotionProductEntity.setMinQuantity(
                Integer.valueOf(child.getValue().get(0).getValueName()));
            promotionProductEntity.setMaxQuantity(
                Integer.valueOf(child.getValue().get(1).getValueName()));
            promotionProductEntity.setProductByProductId(productEntity);
          }
        }
        setProductItem(child, promotionProductEntity);
      }
    }
  }

  public PromotionDto getConditionStatement(UUID id) {
    Optional<PromotionEntity> promotionEntity = promotionRepository.findById(id);
    List<PromotionProductEntity> promotionProductEntities = promotionProductRepository
        .findByPromotionByPromotionId(promotionEntity.get());
    promotionEntity.get().setPromotionItemsByPromotionId(promotionProductEntities);
    PromotionDto promotionDto = modelMapper.map(promotionEntity.get(),
        new TypeToken<PromotionDto>() {
        }.getType());
    setPopertyLabel(promotionDto.getCondition());
    treversalTreeGet(promotionDto.getCondition());
    return promotionDto;
  }

  public void setPopertyLabel(ConditionDto conditionDto) {
    if (conditionDto != null) {
      if (conditionDto.getObjectId() != null) {
        ObjectEntity objectEntity = genericRepository.findById(ObjectEntity.class,
            conditionDto.getObjectId());
        conditionDto.setObjectLabel(objectEntity.getLabel());
        conditionDto.setObject(conditionDto.getObjectId().toString());
      }
      if (conditionDto.getPropertyId() != null) {
        PropertyEntity propertyEntity = genericRepository.findById(PropertyEntity.class,
            conditionDto.getPropertyId());
        conditionDto.setPropertyLabel(propertyEntity.getLabel());
        conditionDto.setProperty(conditionDto.getPropertyId().toString());
      }
      for (ConditionValueDto val : conditionDto.getValue()) {
        if (val.getValueId() != null) {
          val.setValue(val.getValueId());
        }
      }
      for (ConditionDto child : conditionDto.getChildren()) {
        setPopertyLabel(child);
      }
    }
  }

  public static List<PromotionDto> getAllConditionStatement() {
    List<PromotionEntity> promotionEntity = promotionRepository.findAll();
    return modelMapper.map(promotionEntity, new TypeToken<List<PromotionDto>>() {
    }.getType());
  }

  public static SalePromotion getPromotionId(String condition) {
    SalePromotion salePromotion = new SalePromotion();
    List<PromotionDto> query = getAllConditionStatement();

    for (PromotionDto promotionDto : query) {
      String result = buildExpression(promotionDto.getCondition());
      if (result.equals(condition)) {
        salePromotion.setPromotion_id(promotionDto.getPromotionId().toString());
      }
    }
    return salePromotion;
  }

  public List<PromotionBriefDto> getAllPromotion() {
    List<PromotionEntity> promotionEntity = promotionRepository.findAll();
    List<PromotionBriefDto> promotionDtoList = modelMapper.map(promotionEntity, new TypeToken<List<PromotionBriefDto>>() {
    }.getType());

    Collections.reverse(promotionDtoList);

    return promotionDtoList;
  }
}
