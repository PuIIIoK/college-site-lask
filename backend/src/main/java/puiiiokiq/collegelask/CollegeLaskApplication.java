package puiiiokiq.collegelask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "puiiiokiq.collegelask.model")
@EnableJpaRepositories(basePackages = "puiiiokiq.collegelask.repository")
public class CollegeLaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollegeLaskApplication.class, args);
    }
}