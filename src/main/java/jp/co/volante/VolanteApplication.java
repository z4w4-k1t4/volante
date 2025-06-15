package jp.co.volante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VolanteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolanteApplication.class, args);
	}

}
