package spring.repository_layer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;

    @OneToMany
    private List<Offer> offers;

    private String email;

    private boolean enabled;

    public User(String username, String password, String email, Authorities role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = role;
        this.enabled = true;
    }

    @Enumerated
    private Authorities authorities;

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", offers=" + offers +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
