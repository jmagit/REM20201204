package com.example.demo.domain.core;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

public interface DomainService<T, K> {
	List<T> getAll();
	Iterable<T> getAll(Sort sort);
	Page<T> getAll(Pageable pageable);

	Optional<T> getOne(K id);

	T add(T item) throws InvalidDataException;

	T modify(T item) throws NotFoundException, InvalidDataException;

	void delete(T item) throws NotFoundException;
	
	void deleteById(K id) throws NotFoundException;

	Set<ConstraintViolation<T>> getErrors(T item);
	
	boolean isValid(T item);
	
	boolean isInvalid(T item);

}
