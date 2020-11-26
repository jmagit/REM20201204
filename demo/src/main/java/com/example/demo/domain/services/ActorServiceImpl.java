package com.example.demo.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.contracts.ActorService;
import com.example.demo.domain.entities.Actor;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.infraestructure.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	ActorRepository dao;

	@Autowired
	Validator validator;
	
	public Set<ConstraintViolation<Actor>> getErrors(Actor item) {
		return validator.validate(item);
	}

	public boolean isValid(Actor item) {
		return getErrors(item).size() == 0;
	}
	public boolean isInvalid(Actor item) {
		return !isValid(item);
	}
	
	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}
	@Override
	public List<Actor> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getAllIn(Class<T> type) {
		return dao.findByActorIdNotNull(type);
	}

	@Override
	public <T> Iterable<T> getAllIn(Class<T> type, Sort sort) {
		return dao.findByActorIdNotNull(type, sort);
	}

	@Override
	public <T> Page<T> getAllIn(Class<T> type, Pageable pageable) {
		return dao.findByActorIdNotNull(type, pageable);
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	@Transactional
	public Actor add(Actor item) throws InvalidDataException {
		if(isInvalid(item))
			throw new InvalidDataException("Invalid data");
		if(getOne(item.getActorId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		return dao.save(item);
	}
	@Override
	@Transactional
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if(isInvalid(item))
			throw new InvalidDataException("Invalid data");
		if(!getOne(item.getActorId()).isPresent())
			throw new NotFoundException();
		return dao.save(item);
	}
	@Override
	@Transactional
	public void deleteById(Integer id) throws NotFoundException {
		if(!getOne(id).isPresent())
			throw new NotFoundException();
		dao.deleteById(id);
	}
	@Override
	public void delete(Actor item) throws NotFoundException {
		deleteById(item.getActorId());
	}

}