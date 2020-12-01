package com.example.demo.domain.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;

import com.example.demo.domain.core.EntityBase;
import com.example.demo.domain.core.NIF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Persona {
	int id;
	@NotBlank
	String nombre;
	@NIF
	String nif;
	@ISBN
	String cuenta;
}
