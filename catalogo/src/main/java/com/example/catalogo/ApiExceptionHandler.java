package com.example.catalogo;

import javax.annotation.processing.FilerException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.catalogo.application.dtos.ErrorMessage;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler({ NotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler({ BadRequestException.class, MissingRequestHeaderException.class, FilerException.class,
			InvalidDataException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage badRequest(Exception exception) {
		return new ErrorMessage(exception.getMessage(), "");
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage notValidException(MethodArgumentNotValidException exception) {
		StringBuilder sb = new StringBuilder("ERRORES:");
		exception.getBindingResult().getFieldErrors().stream()
				.forEach(err -> sb.append(" " + err.getField() + ": " + err.getDefaultMessage()));
		return new ErrorMessage("Invalid data", sb.toString());
	}

	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorMessage badFormat(Exception exception) {
		return new ErrorMessage("Formato no soportado", "");
	}

}
