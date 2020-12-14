package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.web_layer.config.Constants;

import java.util.Arrays;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Constants.AMAZON_ID = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_ID")).findFirst().get().replace("AMAZON_ID=","");
        Constants.AMAZON_KEY = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_KEY")).findFirst().get().replace("AMAZON_KEY=","");
        Constants.AMAZON_ENDPOINT_URL = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_ENDPOINT_URL")).findFirst().get().replace("AMAZON_ENDPOINT_URL=","");
        Constants.AMAZON_BUCKET_NAME = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_BUCKET_NAME")).findFirst().get().replace("AMAZON_BUCKET_NAME=","");
        Constants.AMAZON_DIR_NAME = Arrays.stream(args).filter(arg -> arg.contains("AMAZON_DIR_NAME")).findFirst().get().replace("AMAZON_DIR_NAME=","");

        Constants.MAILBOX = Arrays.stream(args).filter(arg -> arg.contains("MAILBOX")).findFirst().get().replace("MAILBOX=","");
        Constants.MAILBOX_PASSWORD = Arrays.stream(args).filter(arg -> arg.contains("MAILBOX_PASSWORD")).findFirst().get().replace("MAILBOX_PASSWORD=","");

        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->  logger.info("------STARTING APPLICATION------" );

    }


}
