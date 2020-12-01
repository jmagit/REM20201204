package com.example.catalogo.domain.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.catalogo.application.dtos.FilmShortDTO;
import com.example.catalogo.domain.entities.Language;
import com.example.catalogo.infraestructures.repositories.LanguageRepository;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(path = "/idiomas")
public class LanguageResource {
	@Autowired
	private LanguageRepository dao;

	@GetMapping
	@JsonView(Language.Partial.class)
	public List<Language> getAll() {
		return dao.findAll();
	}

	@GetMapping(path = "/{id}")
	@JsonView(Language.Complete.class)
	public Language getOne(@PathVariable byte id) throws Exception {
		Optional<Language> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get();
	}

	@GetMapping(path = "/{id}/peliculas")
	@Transactional
	public List<FilmShortDTO> getFilms(@PathVariable Byte id) throws Exception {
		Optional<Language> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get().getFilms().stream().map(item -> FilmShortDTO.from(item))
				.collect(Collectors.toList());
	}
	@GetMapping(path = "/{id}/vo")
	@Transactional
	public List<FilmShortDTO> getFilmsVO(@PathVariable Byte id) throws Exception {
		Optional<Language> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get().getFilmsVO().stream().map(item -> FilmShortDTO.from(item))
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Language.Partial.class)
	public ResponseEntity<Object> add(@Valid @RequestBody Language item) throws Exception {
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if (dao.findById(item.getLanguageId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		dao.save(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(item.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{id}")
	@JsonView(Language.Partial.class)
	public Language modify(@PathVariable byte id, @Valid @RequestBody Language item) throws Exception {
		if (item.getLanguageId() != id)
			throw new BadRequestException("No coinciden los ID");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if (!dao.findById(item.getLanguageId()).isPresent())
			throw new NotFoundException();
		dao.save(item);
		return item;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@JsonView(Language.Partial.class)
	public void delete(@PathVariable Byte id) throws Exception {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			throw new InvalidDataException("Missing item", e);
		}
	}

}
