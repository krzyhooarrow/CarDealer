package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.repository_layer.models.Email;
import spring.service_layer.services.MailService;
import spring.service_layer.services.jwt.JwtComponent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokensTest {
    public static void main(String[] args) {
        List<String> recipients = new LinkedList<>();
        recipients.add("auth.mycars@gmail.com");
        sendMail(new Email(new Random().nextLong(),recipients,"testmsh"),"auth.mycars@gmail.com","mycars123");
    }


    private static void sendMail(Email email,String mailbox, String password){
        LoggerFactory.getLogger(MailService.class).info("Sending and email " + email);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailbox, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailbox));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(
                            email.getRecipients().stream().reduce("",(mail1,mail2) -> mail1 +", "+ mail2))
            );

            // test msg
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
