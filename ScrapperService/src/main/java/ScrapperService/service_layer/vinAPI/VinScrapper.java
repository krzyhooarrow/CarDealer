package ScrapperService.service_layer.vinAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VinScrapper {

    private final String apiURL = "https://www.vindecoder.pl/";

    private String setupURL(String VIN) {
        return apiURL + VIN;
    }

    public Map<String, String> getValues(String VIN) throws IOException {
        String URL = setupURL(VIN);

        String[] scrappedParameters = Jsoup.connect(URL)
                .get()
                .getElementsByClass("table")
                .stream()
                .map(el -> el.children()
                        .first()
                        .children()
                        .stream()
                        .map(Element::children)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .map(Elements::eachText)
                .flatMap(List::stream)
                .toArray(String[]::new);

        return IntStream.range(0, scrappedParameters.length / 2)
                .boxed()
                .collect(Collectors.toMap(
                        i -> scrappedParameters[2 * i],
                        i -> scrappedParameters[2 * i + 1],
                        (firstValue, secondValue) -> firstValue + " " + secondValue
                ));
    }


}
