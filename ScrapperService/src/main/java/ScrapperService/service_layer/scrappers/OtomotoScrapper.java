package ScrapperService.service_layer.scrappers;

import ScrapperService.repository_layer.models.OtomotoOffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Lazy
public class OtomotoScrapper implements Observator {

    private final String BASE_LINK = "https://www.otomoto.pl/osobowe/";
    private List<OtomotoOffer> offers = new LinkedList<>();

    public List<OtomotoOffer> scrapOtomotoOffersByMake(String make) throws IOException {
        String query = setupLink(make);

        int pagesCounter = Jsoup.connect(query)
                .get()
                .getElementsByClass("page")
                .stream()
                .map(Element::text)
                .filter(value -> !value.equals("..."))
                .map(Integer::valueOf)
                .max(Comparator.comparing(Integer::valueOf)).orElse(0);

        String pageHref, finalPageHref = (pageHref = Jsoup.connect(query)
                .get()
                .getElementsByClass("next")
                .select("a")
                .stream()
                .map(Element::attributes).map(el -> el.get("href"))
                .findFirst()
                .get()).substring(0, pageHref.length() - 1);


        List<Thread> threadsList = IntStream
                .range(2, pagesCounter)
                .mapToObj(value -> new Thread(new OtomotoPageScrapper(finalPageHref + value, this)))
                .collect(Collectors.toList());

        threadsList.forEach(thread -> {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return offers;
    }

    private String setupLink(@NotNull String make) {    return BASE_LINK + make + '/';   }


    public List<OtomotoOffer> scrapAllOffersFromOtomoto(List<String> makesList) throws IOException {
        return makesList
                .stream()
                .map(make -> {
                    try {
                        return scrapOtomotoOffersByMake(make);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    @Override
    public void updateOfferList(List<OtomotoOffer> offers) {
        this.offers.addAll(offers);
    }
}
