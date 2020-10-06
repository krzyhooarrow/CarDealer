package ScrapperService.service_layer.vinAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VinScrapper {

    private final String apiURL = "https://www.vindecoder.pl/";

    private String setupURL(String VIN) {
        return apiURL + VIN;
    }


    public void getValues(String VIN) throws IOException {

        String URL = setupURL(VIN);

       Jsoup.connect(URL)
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
                .map(Elements::text)
//               .map()
                .peek(System.out::println)
                .collect(Collectors.toList());


    }


}
