package spring.repository_layer.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NonNull
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String action;

    @OneToOne
    private Offer offerId;

    @OneToOne
    private User user;

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public Offer getOfferId() {
        return offerId;
    }

    public History(String action, Offer offerId, User user) {
        this.action = action;
        this.offerId = offerId;
        this.user = user;
        this.date = new Date();
    }

    public User getUser() {
        return user;
    }

    public History(){}
}
