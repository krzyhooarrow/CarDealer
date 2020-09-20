package spring.service_layer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Lazy
@PropertySource("classpath:application.properties")
public class ImagesService {


    @Value("${AWSLogin}")
    private String login;

    @Value("${AWSPassword}")
    private String password;


//    public boolean uploadFile(){}


}
