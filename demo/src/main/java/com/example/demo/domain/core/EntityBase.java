package com.example.demo.domain.core;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EntityBase {
	@JsonIgnore
	@SuppressWarnings("unchecked")
	public <T extends EntityBase> Set<ConstraintViolation<T>> getErrors() {
		return Validation.buildDefaultValidatorFactory().getValidator().validate((T)this);
	}
	
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}

	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}

}
