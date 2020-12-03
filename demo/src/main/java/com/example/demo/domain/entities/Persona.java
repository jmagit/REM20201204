package com.example.demo.domain.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;

import com.example.demo.domain.core.EntityBase;
import com.example.demo.domain.core.NIF;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@ApiModel(value = "Datos de persona", description = "Contiene ...")
public class Persona {
	int id;
	@NotBlank
	String nombre;
	@NIF
	@ApiModelProperty(name = "Numero de identificación fiscal", allowableValues = "1 a 8 digitos con la letra al final")
	String nif;
	
	@ISBN
	String cuenta;
}
