package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.repository_layer.db_init.DBInitializer;
import spring.repository_layer.db_init.RandomDataGenerator;
import spring.web_layer.config.Constants;

import java.util.Arrays;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Constants.AMAZON_ID = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_ID")).findFirst().get().replace("AMAZON_ID=","");
        Constants.AMAZON_KEY = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_KEY")).findFirst().get().replace("AMAZON_KEY=","");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->  logger.info("------STARTING APPLICATION------" );

    }


}
