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

    private ActionType action;

    private Long offerId;

    @OneToOne
    private User user;

    public Date getDate() {
        return date;
    }

    public ActionType getAction() {
        return action;
    }

    public Long getOfferId() {
        return offerId;
    }

    public History(ActionType action, Long offerId, User user) {
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

