package com.example.catalogo.infraestructures.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.catalogo.domain.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
	<T> List<T> findByActorIdNotNull(Class<T> type);
	<T> Page<T> findByActorIdNotNull(Class<T> type, Pageable page);

	@Query("select a from Actor a")
	List<Actor> getAll();

}
