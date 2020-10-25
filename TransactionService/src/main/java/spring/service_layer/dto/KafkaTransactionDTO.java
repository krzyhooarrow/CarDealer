package spring.service_layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaTransactionDTO {

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    public KafkaTransactionDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
