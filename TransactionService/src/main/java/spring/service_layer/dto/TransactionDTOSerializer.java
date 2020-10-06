package spring.service_layer.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TransactionDTOSerializer implements Serializer<TransactionDTO> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, TransactionDTO transactionDTO) {

//        byte[]model;
//        byte[]transmission;
//        byte[]fuelType;
//        int mpg;

        ByteBuffer buffer = ByteBuffer
                .allocate(40 + transactionDTO.getModel().length() + transactionDTO.getGearbox().length() + transactionDTO.getFuelType().length());


        buffer.putLong(transactionDTO.getId());
        buffer.putInt(transactionDTO.getProduction_year());
        buffer.putInt(transactionDTO.getMileage());
        buffer.putFloat(transactionDTO.getCapacity());
        buffer.put(transactionDTO.getModel().getBytes(StandardCharsets.UTF_8));
        buffer.put(transactionDTO.getGearbox().getBytes(StandardCharsets.UTF_8));
        buffer.put(transactionDTO.getFuelType().getBytes(StandardCharsets.UTF_8));


        return buffer.array();
    }

    @Override
    public byte[] serialize(String topic, Headers headers, TransactionDTO data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
