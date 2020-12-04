package com.example.demo.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.entities.dtos.FilmRemotoDTO;

@FeignClient(name="CATALOGO-SERVICE")
public interface CatalogoProxy {
	@GetMapping(path = "/peliculas?mode=short")
	List<FilmRemotoDTO> getPelis();
	@GetMapping(path = "/peliculas/{id}?mode=short")
	FilmRemotoDTO getPeli(@PathVariable int id);
	@GetMapping
	String getCatalogo();
}
