package com.example.demo.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
