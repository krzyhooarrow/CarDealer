package spring.service_layer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class AuthenticationResponse {

    private final String JWT;

    public AuthenticationResponse(String JWT) {
        this.JWT = JWT;
    }
}
