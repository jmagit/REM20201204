package com.example.demo.domain.entities.dtos;

import com.example.demo.domain.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorShortDTO {
	private int actorId;
	private String firstName;
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
