package in.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootEmployeeCrudRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEmployeeCrudRestApiApplication.class, args);
    }

}
