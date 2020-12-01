package com.example.demo.application.resources;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entities.dtos.ErrorMessage;

@RestController
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
}
