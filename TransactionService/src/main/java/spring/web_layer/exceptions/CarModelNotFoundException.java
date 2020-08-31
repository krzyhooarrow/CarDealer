package spring.web_layer.exceptions;

public class CarModelNotFoundException extends Exception{
    public CarModelNotFoundException() {
            super("Car model not found in database");
    }
}
