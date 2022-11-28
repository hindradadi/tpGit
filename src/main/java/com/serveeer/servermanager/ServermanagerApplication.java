package com.serveeer.servermanager;

import com.serveeer.servermanager.enumeration.Status;
import com.serveeer.servermanager.model.Server;
import com.serveeer.servermanager.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServermanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServermanagerApplication.class, args);
	}
   @Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args ->{
			serverRepository.save(new Server(null,"192.168.1.160","Ubuntu Linux","16 gb","pesonal Pc","http://localhost:8080/server/images/server1.png", Status.SERVER_UP));

	};
}
}
