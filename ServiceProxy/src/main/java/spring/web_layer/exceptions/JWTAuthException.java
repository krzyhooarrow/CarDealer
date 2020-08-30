package spring.web_layer.exceptions;

public class JWTAuthException extends Exception {
    public JWTAuthException(String message) {
        super(message);
    }
}
