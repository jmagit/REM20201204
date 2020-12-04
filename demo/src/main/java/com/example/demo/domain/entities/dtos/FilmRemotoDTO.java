package com.example.demo.domain.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FilmRemotoDTO {
	private int filmId;
	private String title;
}
