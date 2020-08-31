package spring.web_layer.exceptions;

public class CarTypeNotFoundException extends Exception {
    public CarTypeNotFoundException(String message) {
        super(message);
    }

    public CarTypeNotFoundException() {
        super("Car type not found in database");
    }
}
