package vlad.avr.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Lab1Application {

	final static Logger logger = LoggerFactory.getLogger(Lab1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@GetMapping("/something")
	public ResponseEntity<String> createLogs() {
		logger.warn("Just checking");
		return ResponseEntity.ok().body("All Ok");
	}
}
