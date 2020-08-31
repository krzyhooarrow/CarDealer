package spring.web_layer.exceptions;

public class OffersNotFoundException extends Exception{
    public OffersNotFoundException(String message) {
        super(message);
    }

    public OffersNotFoundException() {
        super("Offer not found");
    }
}
