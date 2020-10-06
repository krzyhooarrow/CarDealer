package ScrapperService;

import ScrapperService.service_layer.vinAPI.VinApiDecoder;
import ScrapperService.service_layer.vinAPI.VinScrapper;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
       new VinScrapper().getValues("WDD2210261A465977");
    }
}
