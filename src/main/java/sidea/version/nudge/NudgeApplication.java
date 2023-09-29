package sidea.version.nudge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sidea.version.nudge.controller.web.EventTestService;

@EnableScheduling
@SpringBootApplication
public class NudgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NudgeApplication.class, args);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

