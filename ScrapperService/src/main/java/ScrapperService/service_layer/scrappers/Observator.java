package ScrapperService.service_layer.scrappers;

import ScrapperService.repository_layer.models.OtomotoOffer;

import java.util.List;

public interface Observator {

    public void updateOfferList(List<OtomotoOffer> offers);

}
