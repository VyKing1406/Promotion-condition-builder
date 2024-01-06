package promotion.com.conditionbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.UI.OperatorEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, UUID>{
    List<OperatorEntity> findAll();
}