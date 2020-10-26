package ScrapperService.repository_layer.models;

import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class OfferMatches {

    @Id
    @Unique
    private Long offerId;

    @ManyToMany
    private List<OtomotoOffer> offersList;

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
