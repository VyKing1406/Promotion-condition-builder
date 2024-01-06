package promotion.com.conditionbuilder.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.UI.PropertyTypeEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity, UUID>{
    List<PropertyTypeEntity> findAll();
}