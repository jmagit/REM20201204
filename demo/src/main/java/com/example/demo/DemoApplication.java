package com.example.demo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.contracts.ActorService;
import com.example.demo.domain.entities.Actor;
import com.example.demo.domain.entities.dtos.ActorNameDTO;
import com.example.demo.domain.entities.dtos.ActorShortDTO;
import com.example.demo.infraestructure.repositories.ActorRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	ActorRepository dao;
	
	@Autowired
	ActorService srv;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// dao.findAll().stream().forEach(item -> System.out.println(item));
//		Optional<Actor> item = dao.findById(1);
//		if(item.isPresent())
//			System.out.println(item.get());
//		else {
//			System.out.println("No encontrado");
//		}
//		item.get().getFilmActors().stream().forEach(p -> System.out.println(p.getFilm()));
//		Actor item = dao.findById(1).get();
//		item.setFirstName(item.getFirstName().toUpperCase());
//		dao.save(item);
//		dao.findAll().stream().forEach(f -> System.out.println(f));
//		dao.findByFirstNameStartingWithOrLastNameEndingWithOrderByActorIdDesc("pen", "s")
//			.stream().forEach(f -> System.out.println(f));
//		dao.otraQuery(5).stream()
//			.forEach(f -> System.out.println(ActorShortDTO.from(f)));
//		dao.findByActorIdNotNull(ActorShortDTO.class).stream()
//			.forEach(f -> System.out.println(f));
//		dao.findByActorIdNotNull(ActorNameDTO.class).stream()
//			.forEach(f -> System.out.println(f.getNombreCompleto()));
//		Actor item = new Actor(0, "1","");
//		if(item.isInvalid()) {
//			item.getErrors().stream().forEach(err -> System.out.println(err.getPropertyPath() + " --> " + err.getMessage()));
//		} else 
//			dao.save(item);
//		srv.getAll(Sort.by("FirstName","LastName").descending()).stream().forEach(item -> System.out.println(item));
//		srv.add(new Actor(1, "11","AAA"));
	}

}
