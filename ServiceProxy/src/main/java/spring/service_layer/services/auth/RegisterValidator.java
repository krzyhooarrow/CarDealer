package spring.service_layer.services.auth;

import spring.service_layer.dto.UserDTO;

import java.util.regex.Pattern;

// class for validating user register
public class RegisterValidator {

    private final static int usernameMinLength = 5;
    private final static int usernameMaxLength = 15;

    private final static int passwordMinLength = 5;
    private final static int passwordMaxLength = 15;

    private final static Pattern emailPattern =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(UserDTO userDTO){
        return userDTO.getUsername().length() >= usernameMinLength
        && userDTO.getUsername().length() <= usernameMaxLength
        && userDTO.getPassword().length() >= passwordMinLength
        && userDTO.getPassword().length() <= passwordMaxLength
        && emailPattern.matcher(userDTO.getEmail()).find();
    }

}
