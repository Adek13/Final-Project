package com.mbank.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class BankServerApplication {

	private EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		SpringApplication.run(BankServerApplication.class, args);
	}

}
