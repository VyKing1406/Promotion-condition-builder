package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "gender", schema = "public", catalog = "promotion_fresher")
public class GenderEntity {
    @Id
    @Column(name = "gender_id")
    private UUID id;

    @Basic
    @Column(name = "gender_value")
    private String name;

    @OneToMany(mappedBy = "genderByGenderId")
    private Collection<PeopleEntity> gendersByPeopleId;
}
