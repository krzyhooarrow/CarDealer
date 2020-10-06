package ScrapperService.repository_layer.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class OtomotoScrappedPage {

    @Id
    private String make;
    private int numberOfLastScrappedPage;

    public String getMake() {
        return make;
    }

    public int getNumberOfLastScrappedPage() {
        return numberOfLastScrappedPage;
    }

    public void setNumberOfLastScrappedPage(int numberOfLastScrappedPage) {
        this.numberOfLastScrappedPage = numberOfLastScrappedPage;
    }

    public OtomotoScrappedPage(String make, int numberOfLastScrappedPage) {
        this.make = make;
        this.numberOfLastScrappedPage = numberOfLastScrappedPage;
    }
}
