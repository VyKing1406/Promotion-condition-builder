package promotion.com.conditionbuilder.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.UI.PropertyEntity;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID>{
    Optional<PropertyEntity> findById(UUID id);
}