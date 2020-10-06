package spring.service_layer.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TransactionDTODeserializer implements Deserializer<TransactionDTO> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public TransactionDTO deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        TransactionDTO transactionDTO = null;
        try {
            transactionDTO = mapper.readValue(bytes, TransactionDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionDTO;
    }

    @Override
    public TransactionDTO deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
