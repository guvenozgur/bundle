package io.guvenozgur.bundle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class DataHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataHandlerApplication.class, args);
	}

}
