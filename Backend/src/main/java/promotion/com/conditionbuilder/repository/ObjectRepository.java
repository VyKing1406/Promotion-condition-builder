package promotion.com.conditionbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import promotion.com.conditionbuilder.entity.UI.ObjectEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface ObjectRepository extends JpaRepository<ObjectEntity, UUID>{
    List<ObjectEntity> findAll();
}