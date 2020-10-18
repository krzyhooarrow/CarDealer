package spring.service_layer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import spring.service_layer.dto.OfferDTO;

import java.util.Map;

public class OfferDeserializer implements Deserializer<OfferDTO> {

    private boolean isKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.isKey = isKey;
    }

    @Override
    public OfferDTO deserialize(String topic, byte[] data) {
        return null;
    }

    @Override
    public OfferDTO deserialize(String topic, Headers headers, byte[] data) {
        try {
            return objectMapper.readValue(data, OfferDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void close() {
    }
}
