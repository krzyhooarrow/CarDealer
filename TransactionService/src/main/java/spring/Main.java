package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.repository_layer.db_init.DBInitializer;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {   SpringApplication.run(Main.class, args);   }


    @Autowired
    private DBInitializer dbInitializer;

    @Bean
    public CommandLineRunner commandLineRunner() {
//        dbInitializer.initializeDB();
        return args ->  logger.info("------STARTING APPLICATION------");

    }


}
