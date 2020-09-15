package spring.service_layer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationRequest {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
