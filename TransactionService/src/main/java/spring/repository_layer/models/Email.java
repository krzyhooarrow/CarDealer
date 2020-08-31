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
public class Email {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String recipients;

    private String subject;

    private String message;

    public Email(String recipients, String subject, String message) {
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
