package com.dread9182.BTD6API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//todo consider adding the possibility of getting the images for Heroes, Towers, Maps and Bloons suing S3
@SpringBootApplication
public class Btd6ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Btd6ApiApplication.class, args);
	}

}
