package spring.service_layer.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import spring.repository_layer.models.Offer;
import spring.service_layer.dto.OfferDTO;

import java.io.IOException;
import java.util.Map;

public class OfferSerializer implements Serializer<OfferDTO> {

    private boolean isKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.isKey = isKey;
    }

    @Override
    public byte[] serialize(String topic, OfferDTO message) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, OfferDTO message) {
        try {
            return objectMapper.writeValueAsString(message).getBytes();
        } catch (IOException | RuntimeException e) {
            throw new SerializationException("Error serializing value", e);
        }
    }

    @Override
    public void close() {}
}
