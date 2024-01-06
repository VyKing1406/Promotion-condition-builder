package promotion.com.conditionbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.condition.ConditionEntity;

import java.util.UUID;


@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, UUID>{

}