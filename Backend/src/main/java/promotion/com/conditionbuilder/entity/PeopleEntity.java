package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "people", schema = "public", catalog = "promotion_fresher")
public class PeopleEntity {
    @Id
    @Column(name = "people_id")
    private UUID peopleId;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "birth")
    private Date birth;

    @Basic
    @Column(name = "first_time")
    private Boolean firstTime;

    @OneToMany(mappedBy = "peopleByPeopleId")
    private Collection<CartEntity> cartsByPeopleId;
    @ManyToOne
    @JoinColumn(name = "age_group_id", referencedColumnName = "age_group_id", nullable = false)
    private AgeGroupEntity ageGroupByAgeGroupId;
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id", nullable = false)
    private JobEntity jobByJobId;
    @ManyToOne
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id")
    private RankEntity rankByRankId;
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    private GenderEntity genderByGenderId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private AddressEntity addressByAddressId;
    @OneToMany(mappedBy = "peopleByPeopleId")
    private Collection<PromotionPeopleEntity> promotionPeopleByPeopleId;
}
