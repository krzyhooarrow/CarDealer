package spring.web_layer.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User not found in database");
    }
}
