package promotion.com.conditionbuilder.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import promotion.com.conditionbuilder.dto.CartDto;
import promotion.com.conditionbuilder.dto.DeletePromotionDto;
import promotion.com.conditionbuilder.dto.OrderCartDto;
import promotion.com.conditionbuilder.dto.OrderContainerDto;
import promotion.com.conditionbuilder.dto.PromotionBriefDto;
import promotion.com.conditionbuilder.dto.PromotionDto;
import promotion.com.conditionbuilder.dto.UiDto;
import promotion.com.conditionbuilder.entity.ProductEntity;
import promotion.com.conditionbuilder.entity.PromotionEntity;
import promotion.com.conditionbuilder.repository.PromotionProductRepository;
import promotion.com.conditionbuilder.repository.PromotionRepository;
import promotion.com.conditionbuilder.repository.PropertyEntityRespositoryTest;
import promotion.com.conditionbuilder.service.ConditionBuilderService;
import promotion.com.conditionbuilder.service.DroolService;
import promotion.com.conditionbuilder.service.EnrichDataService;
import promotion.com.conditionbuilder.service.UIValueService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/condition-builder")
@CrossOrigin
public class ConditionBuilderController {

  private final DroolService droolService;
  private final ConditionBuilderService conditionBuilderService;
  private final UIValueService uiValueService;
  private final EnrichDataService enrichDataService;
  private final PromotionRepository promotionRepository;
  @Autowired
  private PropertyEntityRespositoryTest propertyEntityRespositoryTest;
  @Autowired
  private PromotionProductRepository promotionProductRepository;

  public ConditionBuilderController(DroolService droolService,
      PromotionRepository promotionRepository, EnrichDataService enrichDataService,
      ConditionBuilderService conditionBuilderService, UIValueService uiValueService) {
    this.droolService = droolService;
    this.conditionBuilderService = conditionBuilderService;
    this.uiValueService = uiValueService;
    this.enrichDataService = enrichDataService;
    this.promotionRepository = promotionRepository;
  }

  @GetMapping("")
  public ResponseEntity<UiDto> getUiValue() {
    try {
      UiDto uiDto = uiValueService.getAllObject();
      return ResponseEntity.ok(uiDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("promotions/{id}/{rule_name}")
  public ResponseEntity<String> deletePromotionId(@PathVariable UUID id,
      @PathVariable String rule_name) {
    try {
      String path = "./src/main/resources/rules/"
          + rule_name
          + ".drl";

      File file = new File(path);
      if (file.delete()) {
        try {
          // promotionProductRepository.deletePromotionProduct(id);
          Optional<PromotionEntity> promotionEntity = promotionRepository.findById(id);
          promotionProductRepository.deleteByPromotionByPromotionId(promotionEntity.get());
          promotionRepository.deleteById(id);
        } catch (Exception e) {
          promotionRepository.deleteById(id);
        }

        return ResponseEntity.ok("Delete successfully: " + id);
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/promotions/{id}")
  public ResponseEntity<String> updatePromotion(@RequestBody PromotionDto promotionDto,
      @PathVariable UUID id) {
    try {
      UUID new_id = conditionBuilderService.savePromotion(promotionDto);

      // String path = "./src/main/resources/rules/"
      // + promotionDto.getName()
      // + ".drl";
      //
      // File file = new File(path);
      // if (file.delete()) {
      try {
        promotionProductRepository.deletePromotionProduct(id);
        promotionRepository.deleteById(id);
      } catch (Exception e) {
        promotionRepository.deleteById(id);
      } finally {
        conditionBuilderService.createDrlFile(promotionDto.getName(),
            promotionDto.getCondition(), new_id,
            promotionDto.getPromotionType().getPromotionTypeName());
      }
      // } else {
      // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      // }
      return ResponseEntity.ok("Update successfully: " + id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/promotions/{id}")
  public ResponseEntity<PromotionDto> getCondiotionById(@PathVariable String id) {
    try {
      PromotionDto promotionDto = conditionBuilderService.getConditionStatement(
          UUID.fromString(id));
      return ResponseEntity.ok(promotionDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/promotions/{id}")
  public ResponseEntity<UUID> updatePromotion(@RequestBody PromotionDto promotionDto,
      @PathVariable String id) {
    try {
      UUID promotionId = conditionBuilderService.updatePromotion(promotionDto, UUID.fromString(id));
      return ResponseEntity.ok(promotionId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/promotions")
  public ResponseEntity<UUID> postMethodName(@RequestBody PromotionDto promotionDto) {
    try {
      UUID id = conditionBuilderService.savePromotion(promotionDto);
      conditionBuilderService.createDrlFile(promotionDto.getName(), promotionDto.getCondition(), id,
          promotionDto.getPromotionType().getPromotionTypeName());
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // API Testing
  @PostMapping("/enrich-data")
  public ResponseEntity<OrderCartDto> enrichData(@RequestBody CartDto cartDto) {
    OrderCartDto orderDto = enrichDataService.enrichCart(cartDto);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping("/sale-promotion")
  public ResponseEntity<List<PromotionDto>> salePromotion(@RequestBody CartDto cartDto) {
    try {
      OrderCartDto orderDto = enrichDataService.enrichCart(cartDto);
      OrderContainerDto orderCartDto = droolService.getPromotion(orderDto);
      List<PromotionDto> promotionDtos = enrichDataService.enrichPromotion(orderCartDto);
      return ResponseEntity.ok(promotionDtos);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  @GetMapping("/a")
  public PromotionEntity aaa() {
    try {
      Optional<PromotionEntity> proOptional = promotionRepository.findById(UUID.randomUUID());
      return proOptional.get();
    } catch (Exception e) {
      System.out.println(e.getClass());
    }
    return null;
  }
}