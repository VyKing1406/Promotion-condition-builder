package promotion.com.conditionbuilder.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import promotion.com.conditionbuilder.entity.UI.PropertyEntity;

public interface PropertyEntityRespositoryTest extends JpaRepository<PropertyEntity, UUID> {

}
