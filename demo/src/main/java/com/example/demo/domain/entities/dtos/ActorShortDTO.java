package com.example.demo.domain.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.domain.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorShortDTO {
	private int actorId;
	@NotBlank
	@Size(min = 2, max = 45)
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 45)
	@Pattern(regexp = "^[A-Z]+$")
	private String lastName;
	
	public static Actor from(ActorShortDTO source) {
		return new Actor(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
	}
	public static ActorShortDTO from(Actor source) {
		return new ActorShortDTO(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
	}
}
