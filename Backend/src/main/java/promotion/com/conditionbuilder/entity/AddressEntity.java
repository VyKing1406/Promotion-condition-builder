package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "address", schema = "public", catalog = "promotion_fresher")
public class AddressEntity {
    @Id
    @Column(name = "address_id")
    private UUID id;

    @Basic
    @Column(name = "address_value")
    private String value;

    @OneToMany(mappedBy = "addressByAddressId")
    private Collection<PeopleEntity> peoplesByAddressId;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id", nullable = false)
    private ProvinceEntity provinceByProvinceId;
}
