package spring.service_layer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring.repository_layer.models.Offer;
import spring.repository_layer.models.User;
import spring.repository_layer.models.cars.Car;


import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Data
public class OfferDTO {

//    public static Function<? super Offer,?> mapToOfferDTO;
    private Car car;
    private Integer price;
    private String description;
    private List<String> image;
    private User user;


}
