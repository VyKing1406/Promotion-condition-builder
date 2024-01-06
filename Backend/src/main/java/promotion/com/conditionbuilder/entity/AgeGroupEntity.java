package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "age_group", schema = "public", catalog = "promotion_fresher")
public class AgeGroupEntity {
    @Id
    @Column(name = "age_group_id")
    private UUID id;

    @Basic
    @Column(name = "age_group_name")
    private String name;

    @Basic
    @Column(name = "start_age")
    private Integer startAge;

    @Basic
    @Column(name = "end_age")
    private Integer endAge;

    @OneToMany(mappedBy = "ageGroupByAgeGroupId")
    private Collection<PeopleEntity> peopleByAgeGroupId;
}
