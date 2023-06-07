package skillguru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "skillguru")
public class SkillGuruServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillGuruServerApplication.class, args);
    }

}