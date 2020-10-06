package ScrapperService.repository_layer.models;

import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class OfferMatches {

    @Id
    private Long id;

    @OneToMany
    private List<OtomotoOffer> offersList;


    public Long getId() {
        return id;
    }

    public List<OtomotoOffer> getOffersList() {
        return offersList;
    }

    public OfferMatches(Long id, List<OtomotoOffer> offersList) {
        this.id = id;
        this.offersList = offersList;
    }
}
