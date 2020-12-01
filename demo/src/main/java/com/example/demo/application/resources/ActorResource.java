package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.contracts.ActorService;
import com.example.demo.domain.entities.Actor;
import com.example.demo.domain.entities.Film;
import com.example.demo.domain.entities.dtos.ActorShortDTO;
import com.example.demo.domain.entities.dtos.FilmShortDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/actores")
public class ActorResource {
	@Autowired
	ActorService srv;
	
	@GetMapping
	public List<ActorShortDTO> getAll() {
		return srv.getAllIn(ActorShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	public ActorShortDTO getOne(@PathVariable int id) throws NotFoundException {
		Optional<Actor> item = srv.getOne(id);
		if(!item.isPresent())
			throw new NotFoundException();
		return ActorShortDTO.from(item.get());
	}
	
	@GetMapping(path = "/{id}/peliculas")
	public List<FilmShortDTO> getPelis(@PathVariable int id) throws NotFoundException {
		Optional<Actor> item = srv.getOne(id);
		if(!item.isPresent())
			throw new NotFoundException();
		return item.get().getFilmActors().stream().map(f->FilmShortDTO.from(f.getFilm())).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorShortDTO item) throws BadRequestException, InvalidDataException {
		Actor newItem = ActorShortDTO.from(item);
		if(newItem.isInvalid())
			throw new BadRequestException(newItem.getErrorsString());
		newItem = srv.add(newItem);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody ActorShortDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
//		Actor newItem = ActorShortDTO.from(item);
//		if(newItem.isInvalid())
//			throw new BadRequestException("Invalid data");
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(ActorShortDTO.from(item));	
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws NotFoundException {
		srv.deleteById(id);
	}

}
