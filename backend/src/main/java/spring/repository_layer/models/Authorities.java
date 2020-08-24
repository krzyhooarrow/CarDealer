package spring.repository_layer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public enum Authorities {

    USER,ADMIN;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;



}
