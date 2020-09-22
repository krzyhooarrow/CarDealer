package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.repository_layer.db_init.DBInitializer;
import spring.repository_layer.db_init.RandomDataGenerator;

import java.util.Arrays;

@RestController
@RequestMapping("/database")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class InitializationController {

    private static DBInitializer dbInitializer;
    private static RandomDataGenerator generator;

    @GetMapping("/init")
    public void initDataBase(){
         dbInitializer.initializeCarsWithTheirMakesAndModels();
    }

    @GetMapping("/random")
    public void createRandomData(){
        generator.initializeRandomData(2000);
    }
}
