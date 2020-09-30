package ScrapperService.service_layer.scrappers;

import ScrapperService.repository_layer.models.OtomotoOffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class OtomotoPageScrapper implements Runnable {

    private List<OtomotoOffer> offers;
    private final String url;
    private final Observator observator;
    private final Logger logger = LoggerFactory.getLogger(OtomotoPageScrapper.class);

    public OtomotoPageScrapper(String url, Observator observator) {
        this.url = url;
        this.observator = observator;
    }

    @Override
    public void run() {
        try {
            offers = scrapLinksToOffers(url).stream().map(url -> {
                try {
                    return getBaseCarParameters(url);
                } catch (IOException e) {
                    return null;
                }
            }).filter(Objects::nonNull).map(OtomotoOffer::new).collect(Collectors.toList());

            observator.updateOfferList(offers);
            logger.info("Page has been scrapped " + Thread.currentThread().getName() + " with "+ offers +" offers");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> scrapLinksToOffers(String baseLink) throws IOException {
        return Jsoup.connect(baseLink)
                .get()
                .getElementsByClass("offer-title__link")
                .stream()
                .map(div -> div.attributes().get("href"))
                .collect(Collectors.toList());
    }

    private Map<String, String> getBaseCarParameters(String url) throws IOException {
        Document document = Jsoup.connect(url).get();


        List<String> labels = new LinkedList<>();
        List<String> values = new LinkedList<>();
        Map<String, String> parameters = new HashMap<>();

        document.getElementsByClass("offer-params__label").forEach(e -> labels.add(e.text().trim()));
        document.getElementsByClass("offer-params__value").forEach(e -> values.add(e.text().trim()));

        for (int i = 0; i < labels.size(); i++) if (values.size() > i) parameters.put(labels.get(i), values.get(i));

        parameters.put("Cena", document.getElementsByClass("offer-price__number")
                .get(0)
                .text()
                .trim()
                .replace("PLN", "")
                .replace(" ", ""));
        parameters.put("URL",url);

        return parameters;
    }
}
