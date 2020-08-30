package spring.service_layer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {

    private String username;
    private String password;
    private String email;


}
