package ScrapperService.service_layer.vinAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class VinApiDecoder {

    private final String apiURL = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevin/";

    private String setupURL(String VIN) {
        return apiURL + VIN + "*BA?format=json";
    }

    private VIN getVIN(String VIN) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(setupURL(VIN));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return new Gson().fromJson(result.toString(), VIN.class);
    }


    public Map<String, String> getDataAsMap(String VIN) throws IOException {
        return getVIN(VIN).Results
                .stream()
                .filter(map -> map.get("Variable")!=null && map.get("Value")!=null)
                .collect(Collectors.toMap(map -> map.get("Variable"), map -> map.get("Value")));
    }


    private static class VIN {
        private int Count;
        private String Message;
        private String SearchCriteria;
        private List<Map<String, String>> Results;

        public int getCount() {
            return Count;
        }

        public String getMessage() {
            return Message;
        }

        public String getSearchCriteria() {
            return SearchCriteria;
        }

        public List<Map<String, String>> getResults() {
            return Results;
        }

        @Override
        public String toString() {
            return "VIN{" +
                    "Count=" + Count +
                    ", Message='" + Message + '\'' +
                    ", SearchCriteria='" + SearchCriteria + '\'' +
                    ", Results=" + Results +
                    '}';
        }
    }
}
