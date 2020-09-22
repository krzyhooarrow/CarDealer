package spring.repository_layer.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RedisHash("subs")
@NoArgsConstructor
@Data
public class UserSubscriptions {

    @Id
    private Long id;

    @ElementCollection
    private Set<Long> subscribedOffers;

    public Set<Long> getSubscribedOffers() {
        return subscribedOffers;
    }

    public UserSubscriptions(Long id) {
        this.id = id;
        this.subscribedOffers = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setSubscribedOffers(Set<Long> subscribedOffers) {
        this.subscribedOffers = subscribedOffers;
    }
}
