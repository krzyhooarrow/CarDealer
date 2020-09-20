package spring.service_layer.dto;

import spring.repository_layer.models.History;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;


import javax.persistence.TemporalType;
import java.util.Date;

public class HistoryDTO {

    private Date date;
    private String action;
    private Long offerId;

    public HistoryDTO(History history) {

        this.date = history.getDate();
        this.action = history.getAction();
        this.offerId = history.getOfferId().getId();
    }

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public Long getOfferId() {
        return offerId;
    }
}
