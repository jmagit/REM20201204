package com.example.catalogo.application.dtos;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ActorNameDTO {
	@JsonProperty("id")
	int getActorId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	@JsonProperty("texto")
	String getNombreCompleto();
}
