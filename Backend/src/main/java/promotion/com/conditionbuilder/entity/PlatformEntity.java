package promotion.com.conditionbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "platform", schema = "public", catalog = "promotion_fresher")
public class PlatformEntity {
    @Id
    @Column(name = "platform_id")
    private UUID platformId;

    @Basic
    @Column(name = "platform_name")
    private String platformName;

    @OneToMany(mappedBy = "platformByPlatformId")
    private Collection<ChannelEntity> channelsByPlatformId;
}
