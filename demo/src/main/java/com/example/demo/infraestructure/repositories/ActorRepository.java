package com.example.demo.infraestructure.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstNameStartingWithOrLastNameEndingWithOrderByActorIdDesc(String prefijos, String sugijo);
	@Query("from Actor where actorId < ?1")
	List<Actor> otraQuery(int id);
	
	<T> List<T> findByActorIdNotNull(Class<T> type);
	<T> List<T> findByActorIdNotNull(Class<T> type, Sort sort);
	<T> Page<T> findByActorIdNotNull(Class<T> type, Pageable pageable);
}
