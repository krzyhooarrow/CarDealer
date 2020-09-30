package ScrapperService.repository_layer;

import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.service_layer.dto.CarDTO;

import java.util.stream.Stream;

public final class OffersComparator {

    //coefficients for comparing offers
    private static final float a=1F,b=1F,c=1F,d=0.1F;

    public static float compare(OtomotoOffer offer, CarDTO carDTO){
        return Stream.of(compareMileage(offer,carDTO),
                comparePrice(offer, carDTO),
                compareCapacity(offer, carDTO),
                compareProductionYear(offer, carDTO))
                .reduce(Float::sum)
                .get();
    }

    private static float compareMileage(OtomotoOffer offer, CarDTO carDTO){
        return a*Math.abs(offer.getMileage()-carDTO.getMileage())/ (float) carDTO.getMileage();
    }

    private static float comparePrice(OtomotoOffer offer, CarDTO carDTO){
        return b*Math.abs(offer.getPrice()-carDTO.getPrice())/ (float) carDTO.getPrice();
    }

    private static float compareCapacity(OtomotoOffer offer, CarDTO carDTO){
        return c*Math.abs(offer.getCapacity()-carDTO.getCapacity())/ carDTO.getCapacity();
    }

    private static float compareProductionYear(OtomotoOffer offer, CarDTO carDTO){
        return d*Math.abs(offer.getProductionYear()-carDTO.getProductionYear());
    }

}
