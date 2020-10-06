package ScrapperService.repository_layer.models;

import ScrapperService.repository_layer.PatternMatcher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class OtomotoOffer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private int power;
    private int mileage;
    private boolean isNew;
    private String type;
    private int price;
    private float capacity;
    private String url;
    private String fuelType;
    private String make;
    private String country;
    private String model;
    private int productionYear;
    private Transmission transmission;
    private String color;


    public OtomotoOffer(Map<String,String> parameters){
        try {
            this.power = PatternMatcher.matchInteger(parameters.get("Moc"));
            this.mileage = PatternMatcher.matchInteger(parameters.get("Przebieg"));
            this.isNew = !parameters.get("Stan").equals("Używane");
            this.type = parameters.get("Typ");
            this.price = PatternMatcher.matchInteger(parameters.get("Cena"));
            this.capacity = Math.round(PatternMatcher.matchInteger(parameters.get("Pojemność skokowa"))/100F)/10F;
            this.url = parameters.get("URL");
            this.fuelType = parameters.get("Rodzaj paliwa");
            this.make = parameters.get("Marka pojazdu");
            this.country = parameters.get("Kraj pochodzenia");
            this.model = parameters.get("Model pojazdu");
            this.productionYear = PatternMatcher.matchInteger(parameters.get("Rok produkcji"));
            this.transmission = PatternMatcher.matchByPattern(parameters.get("Skrzynia biegów"),"Manualna")!=null
                    ? Transmission.manual:Transmission.automatic;
            this.color = parameters.get("Kolor");
        }catch (NullPointerException | NumberFormatException ignored){}
    }

    public OtomotoOffer(){}

    public int getPower() {
        return power;
    }

    public int getMileage() {
        return mileage;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getUrl() {
        return url;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getMake() {
        return make;
    }

    public String getCountry() {
        return country;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", mileage=" + mileage +
                ", isNew=" + isNew +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", url='" + url + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", make='" + make + '\'' +
                ", country='" + country + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", transmission=" + transmission +
                ", color='" + color + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
}
