package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
@Data
@Entity
@Table(name = "job", schema = "public", catalog = "promotion_fresher")
public class JobEntity {
    @Id
    @Column(name = "job_id")
    private UUID id;

    @Basic
    @Column(name = "job_name")
    private String name;

    @OneToMany(mappedBy = "jobByJobId")
    private Collection<PeopleEntity> peopleByJobId;
}
