package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpReceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpReceptorApplication.class, args);
	}


}
