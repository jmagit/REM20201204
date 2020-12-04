package com.example.demo.application.resources;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.application.proxies.CatalogoProxy;
import com.example.demo.domain.entities.Persona;
import com.example.demo.domain.entities.dtos.ErrorMessage;
import com.example.demo.domain.entities.dtos.FilmRemotoDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Demostraciones del curso", description = "La literatura ....")
public class DemosResource {

	@GetMapping("/cotilla/{id}")
	public String cotilla(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language, 
	        @CookieValue(name = "XSRF-TOKEN", required = false, defaultValue = "no presente") String cookie) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    sb.append("cookie: " + cookie + "\n");
	    return sb.toString();
	}

	@GetMapping(path = "/formato", produces = "application/json")
	public ErrorMessage producesJSON() {
		return new ErrorMessage("Esto es JSON", "");
	}
	@GetMapping(path = "/formato", produces = "application/xml")
	public ErrorMessage producesXML() {
		return new ErrorMessage("Esto es XML", "");
	}
	@GetMapping(path = "/formato", produces = "text/plain")
	public String producesText() {
		return "Esto es Text";
	}
	
	@GetMapping(path = "/persona")
	public Persona persona() {
		return new Persona(666,"ddd","NIF", "");
	}
	@PostMapping(path = "/persona")
	@ApiOperation(value = "Prueba de la validación del nif")
	@ApiResponses({
		@ApiResponse(code=200, message = "los datos son validos"),
		@ApiResponse(code=400, message = "no cumple las reglas de validación")
	})
	public void persona(@ApiParam(value = "Datos de la persona") @Valid @RequestBody Persona item) {
	}

	@Autowired
	RestTemplate srv;
	
	@GetMapping("/pelis")
	public List<FilmRemotoDTO> getPelis() throws NotFoundException, BadRequestException {
		try {
			ResponseEntity<List<FilmRemotoDTO>> response = srv.exchange(
					"http://localhost:8080/catalogo/peliculas?mode=short", 
					HttpMethod.GET,
					HttpEntity.EMPTY, 
					new ParameterizedTypeReference<List<FilmRemotoDTO>>() {	}
					);
			return response.getBody();
		} catch (HttpClientErrorException ex) {
			if( ex.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new NotFoundException();
			else {
				throw new BadRequestException(ex.getMessage());
			}
		}
	}
	
	@GetMapping("/pelis/{id}")
	public FilmRemotoDTO getPelis(@PathVariable int id) throws NotFoundException, BadRequestException {
		try {
		return srv.getForObject(
				"http://CATALOGO-SERVICE/peliculas/{id}?mode=short", 
				FilmRemotoDTO.class, 
				id);
		
		} catch (HttpClientErrorException ex) {
			if( ex.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new NotFoundException();
			else {
				throw new BadRequestException(ex.getMessage());
			}
		}
	}
	
	@Autowired
	CatalogoProxy proxy;

	@GetMapping("/peliculas")
	public List<FilmRemotoDTO> getPeliculas() throws NotFoundException, BadRequestException {
		return proxy.getPelis();
	}
	
	@GetMapping("/peliculas/{id}")
	public FilmRemotoDTO getPeliculas(@PathVariable int id) throws NotFoundException, BadRequestException {
		return proxy.getPeli(id);
	}
	@GetMapping("/cliente")
	public String getCliente() throws NotFoundException, BadRequestException {
		return srv.getForObject(
				"http://CATALOGO-SERVICE/", 
				String.class);
//		return proxy.getCatalogo();
	}
}
