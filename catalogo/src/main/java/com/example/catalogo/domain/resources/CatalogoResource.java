package com.example.catalogo.domain.resources;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.catalogo.application.dtos.NovedadesDTO;

import lombok.Value;

@RestController
@RequestMapping(path = "/")
public class CatalogoResource {
	@Autowired
	private FilmResource filmSrv;
	@Autowired
	private ActorResource artorSrv;
	@Autowired
	private CategoryResource categorySrv;

	@Value
	public class CatalogoResources {
		@Value
		public class CatalogoLinks {
			public class Href {
				private String href;
				public String getHref() { return href; }
				public Href(String path) {
					href = ServletUriComponentsBuilder.fromCurrentRequest().path(path).toUriString();
				}
			}
			private Href self = new Href("");
			private Href actores = new Href("/actores");
			private Href peliculas = new Href("/peliculas");
			private Href categorias = new Href("/categorias");
			private Href idiomas = new Href("/idiomas");
			private Href novedades = new Href("/novedades");
		}

		private CatalogoLinks _links = new CatalogoLinks();
	}

	@GetMapping(path = "/")
	public ResponseEntity<CatalogoResources> getResources() {
		return ResponseEntity.ok().header("Content-Type", "application/hal+json").body(new CatalogoResources());
	}
	
	@GetMapping(path = "/novedades")
	public NovedadesDTO novedades(@RequestParam(required = false) Timestamp fecha) {
		// Timestamp fecha = Timestamp.valueOf("2019-01-01 00:00:00");
		if(fecha == null)
			fecha = Timestamp.from(Instant.now().minusSeconds(360));
		return new NovedadesDTO(filmSrv.novedades(fecha), artorSrv.novedades(fecha), categorySrv.novedades(fecha));
	}

}
