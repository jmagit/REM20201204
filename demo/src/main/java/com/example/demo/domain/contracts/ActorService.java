package com.example.demo.domain.contracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.core.DomainService;
import com.example.demo.domain.entities.Actor;

public interface ActorService extends DomainService<Actor, Integer>{
	<T> List<T> getAllIn(Class<T> type);
	<T> Iterable<T> getAllIn(Class<T> type, Sort sort);
	<T> Page<T> getAllIn(Class<T> type, Pageable pageable);
}