package com.example.demo;

import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.entities.Actor;
import com.example.demo.infraestructure.repositories.ActorRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	ActorRepository dao;

	@Override
	public void run(String... args) throws Exception {
		
		dao.findAll().stream().forEach(item -> System.out.println(item));
		Optional<Actor> item = dao.findById(-1);
		if(item.isPresent())
			System.out.println(item.get());
		else {
			System.out.println("No encontrado");
		}
	}

}
