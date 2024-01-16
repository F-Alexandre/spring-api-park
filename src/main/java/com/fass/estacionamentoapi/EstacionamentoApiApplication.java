package com.fass.estacionamentoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstacionamentoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoApiApplication.class, args);
		System.out.println("SETUP APPLICATION PROPERTIES");
	}

}
