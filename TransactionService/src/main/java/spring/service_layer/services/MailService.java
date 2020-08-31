package spring.service_layer.services;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spring.repository_layer.models.Email;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Lazy
@Service
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class MailService {

    @Value("mailbox")
    private String mailbox;

    @Value("mailboxPassword")
    private String password;

    private Session session;

    private final Locale locale = new Locale("pl", "PL");
    private final ResourceBundle notificationsBundle = ResourceBundle.getBundle("bundles/messages", locale);

    @PostConstruct
    public void init() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailbox, password);
                    }
                });
    }


    private void sendMail(Email email) {

        LoggerFactory.getLogger(MailService.class).info("Sending an email " + email);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailbox));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(
                            email.getRecipients()
                    ));

            message.setSubject(email.getSubject());
            message.setText(email.getMessage());

            Transport.send(message);

        } catch (MessagingException e) {
            LoggerFactory.getLogger(MailService.class).info("Error sending an email " + email + "\n" + e.getMessage());
        }
    }

    @Async
    public void sendMail(String recipients, NotificationType type) {
        sendMail(new Email(recipients, getSubject(type), getMessage(type)));
    }

    private String getSubject(NotificationType type) {
        if (type.equals(NotificationType.OFFER_CREATION)) {
            return notificationsBundle.getString("OFFER_CREATION_SUBJECT");
        } else {
            return notificationsBundle.getString("OFFER_REMOVAL_SUBJECT");
        }
    }

    private String getMessage(NotificationType type) {
        if (type.equals(NotificationType.OFFER_CREATION)) {
            return notificationsBundle.getString("OFFER_CREATION_MESSAGE");
        } else {
            return notificationsBundle.getString("OFFER_REMOVAL_MESSAGE");
        }
    }

    public enum NotificationType {OFFER_REMOVAL, OFFER_CREATION}

}
