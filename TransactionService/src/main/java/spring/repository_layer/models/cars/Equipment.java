package spring.repository_layer.models.cars;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public Equipment(String name) {
        this.name = name;
    }
}
