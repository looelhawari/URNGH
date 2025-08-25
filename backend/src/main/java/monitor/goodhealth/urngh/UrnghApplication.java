package monitor.goodhealth.urngh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UrnghApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrnghApplication.class, args);
    }
}
