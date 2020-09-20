package spring.repository_layer.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

//@Entity

@RedisHash("offer_popularity")
@NoArgsConstructor
@Data
public class OfferPopularity {

    @Id
    private Long id;

    @Column(columnDefinition = "integer default 0")
    private Integer visitsCounter;


    public int getVisitsCounter() {  return visitsCounter;  }

    public void setVisitsCounter(int visitsCounter) { this.visitsCounter = visitsCounter;  }

    public Long getId() {   return id;  }

    public OfferPopularity(Long id) {
        this.id = id;
        this.visitsCounter = 0;
    }
}
