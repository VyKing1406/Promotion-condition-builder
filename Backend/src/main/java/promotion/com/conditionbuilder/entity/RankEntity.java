package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "rank", schema = "public", catalog = "promotion_fresher")
public class RankEntity {
    @Id
    @Column(name = "rank_id")
    private UUID id;

    @Basic
    @Column(name = "rank_name")
    private String name;

    @OneToMany(mappedBy = "rankByRankId")
    private Collection<PeopleEntity> peopleByRankId;
}
