package com.example.demo.infraestructure.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Byte>  {
	@RestResource(path = "novedades")
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
	@RestResource(exported = false)
	@Override
	void deleteById(Byte id);
}
