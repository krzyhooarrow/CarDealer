package ScrapperService.repository_layer.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class OfferMatches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long offerId;

    @ManyToMany
    private List<OtomotoOffer> offersList;

    public Long getId() {
        return id;
    }

    public List<OtomotoOffer> getOffersList() {
        return offersList;
    }

    public OfferMatches(Long id, List<OtomotoOffer> offersList) {
        this.offerId = id;
        this.offersList = offersList;
    }

    public Long getOfferId() {
        return offerId;
    }
}
