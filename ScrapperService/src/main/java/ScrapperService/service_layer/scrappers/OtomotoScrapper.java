package ScrapperService.service_layer.scrappers;

import ScrapperService.repository_layer.models.OtomotoOffer;
import ScrapperService.repository_layer.models.OtomotoScrappedPage;
import ScrapperService.repository_layer.repositories.OtomotoOfferRepository;
import ScrapperService.repository_layer.repositories.ScrappedPagesRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@Lazy
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OtomotoScrapper implements Observator {

    private final String BASE_LINK = "https://www.otomoto.pl/osobowe/";
    private final List<OtomotoOffer> offers = new LinkedList<>();
    private ScrappedPagesRepository pagesRepository;
    private OtomotoOfferRepository otomotoOfferRepository;

    public void scrapOtomotoOffersByMake(String make) throws IOException {
        String linkToOffersList = setupLink(make);

        int pagesCounter = Jsoup.connect(linkToOffersList)
                .get()
                .getElementsByClass("page")
                .stream()
                .map(Element::text)
                .filter(value -> !value.equals("..."))
                .map(Integer::valueOf)
                .max(Comparator.comparing(Integer::valueOf)).orElse(0);

        String pageHref, finalPageHref = (pageHref = Jsoup.connect(linkToOffersList)
                .get()
                .getElementsByClass("next")
                .select("a")
                .stream()
                .map(Element::attributes).map(el -> el.get("href"))
                .findFirst()
                .get()).substring(0, pageHref.length() - 1);


        ExecutorService singleThreadPageScrapperExecutor = Executors.newSingleThreadExecutor();

        IntStream.range(getLastScrappedPageNumberByMake(make), pagesCounter)
                .boxed()
                .forEach(
                        value -> {
                            try {
                                List<OtomotoOffer> scrappedOffers = singleThreadPageScrapperExecutor.submit(new OtomotoPageScrapper(finalPageHref + value)).get();
                                otomotoOfferRepository.saveAll(scrappedOffers);
                                saveScrappedPageNumberByMake(make, value);
                            } catch (Exception e) {}
                        });

        singleThreadPageScrapperExecutor.shutdown();
    }

    private String setupLink(@NotNull String make) {
        return BASE_LINK + make + '/';
    }

    @Override
    public void updateOfferList(List<OtomotoOffer> offers) {
        this.offers.addAll(offers);
    }

    private void saveScrappedPageNumberByMake(String make, int pageNumber) {
        OtomotoScrappedPage page = pagesRepository.getByMake(make).orElse(new OtomotoScrappedPage(make, pageNumber));
        if (pageNumber > page.getNumberOfLastScrappedPage()) {
            page.setNumberOfLastScrappedPage(pageNumber);
            pagesRepository.save(page);
        }
    }

    private Integer getLastScrappedPageNumberByMake(String make) {
        return pagesRepository.getLastScrappedPageByMake(make).orElse(2);
    }

    public void scrapAllOtomotoMakes()  {
        Stream.of(
//                "ford",
//                "audi",
//                "bmw",
//                "chevrolet",
//                "citroen",
//                "dacia",
//                "hyundai",
//                "kia",
//                "mazda",
//                "mercedes-benz",
//                "mitsubishi",
//                "nissan",
//                "opel",
                "peugeot",
                "porsche",
                "renault",
                "skoda",
                "suzuki",
                "toyota",
                "volkswagen",
                "volvo"
        ).forEach(carMake -> {
            try {
                scrapOtomotoOffersByMake(carMake);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
