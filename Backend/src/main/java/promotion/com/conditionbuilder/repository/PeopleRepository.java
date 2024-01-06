package promotion.com.conditionbuilder.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import promotion.com.conditionbuilder.entity.PeopleEntity;

public interface PeopleRepository extends JpaRepository<PeopleEntity, UUID> {

}
