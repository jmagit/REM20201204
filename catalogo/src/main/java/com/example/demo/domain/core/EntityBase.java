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
	public String getErrorsString() {
		Set<ConstraintViolation<EntityBase>> lst = getErrors();
		if(lst.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("ERRORES:");
		lst.stream().forEach(err -> sb.append(" " + err.getPropertyPath() + ": " + err.getMessage()));
		return sb.toString();
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
