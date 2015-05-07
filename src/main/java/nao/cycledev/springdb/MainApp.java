package nao.cycledev.springdb;

import nao.cycledev.springdb.data.SpitterRepository;
import nao.cycledev.springdb.model.Spitter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {


    public static void main(String[] args) throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                nao.cycledev.springdb.config.DataSourceConfig.class);

        SpitterRepository spitterRepository = (SpitterRepository)context.getBean("jpaSpitterRepository");

        //Spitter spitter = new Spitter(5, "111", "222", "111.222");
        Spitter spitter = spitterRepository.findSpitter(5);
        System.out.println(spitter);

    }
}
