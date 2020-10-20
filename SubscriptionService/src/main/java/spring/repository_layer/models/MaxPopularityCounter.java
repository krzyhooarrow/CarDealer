package spring.repository_layer.models;


import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
public class MaxPopularityCounter {

   @Id
   Long offerId;

   int counter;

   @Column(unique = true)
   Type type;


    public MaxPopularityCounter(Long offerId, int counter, Type type) {
        this.offerId = offerId;
        this.counter = counter;
        this.type = type;
    }

    public Long getOfferId() {
        return offerId;
    }

    public int getCounter() {
        return counter;
    }

    public Type getType() {
        return type;
    }
}
