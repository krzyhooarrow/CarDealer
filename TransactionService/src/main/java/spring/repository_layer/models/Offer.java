package spring.repository_layer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import spring.repository_layer.models.cars.Car;
import spring.repository_layer.models.cars.ConcreteCar;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
//@JsonSerialize(using = OfferSerializer.class)
public class Offer  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "concrete_car_id")
    private ConcreteCar car;

    private Integer price;

    private String description;

    @ElementCollection
    private List<String> image;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.DATE)
    private Date offerCreationTime;

    private String title;

    private String tags;

    public Offer(ConcreteCar car, Integer price, String description, List<String> image, User user) {
        this.car = car;
        this.price = price;
        this.description = description;
        this.image = image;
        this.user = user;
    }

    public ConcreteCar getCar() {
        return car;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImage() {
        return image;
    }

    public User getUser() {
        return user;
    }

    public Date getOfferCreationTime() {
        return offerCreationTime;
    }

    public Long getId() {  return id;  }

    public String getTitle() {  return title;   }

    public String getTags() {   return tags;   }


}
