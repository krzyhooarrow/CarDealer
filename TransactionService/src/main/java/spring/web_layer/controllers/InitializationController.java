package spring.web_layer.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.repository_layer.db_init.DBInitializer;
import spring.repository_layer.db_init.random_data.RandomDataGenerator;

@RestController
@RequestMapping("/database")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class InitializationController {

    private DBInitializer dbInitializer;
    private RandomDataGenerator generator;

    @GetMapping("/init")
    public boolean initDataBase(){
         dbInitializer.initializeCarsWithTheirMakesAndModels();
         return true;
    }

    @GetMapping("/random")
    public boolean createRandomData(){
        generator.initializeRandomData(2000);
        return true;
    }
}
