package spring.web_layer.exceptions;

public class InvalidUsernameOrPasswordException extends Exception {
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}
