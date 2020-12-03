package com.example.demo.domain.entities.dtos;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domain.entities.Film;

@Projection(name = "peli-corta", types = Film.class)
public interface FilmCorto {
	int getFilmId();
	String getTitle();
}
