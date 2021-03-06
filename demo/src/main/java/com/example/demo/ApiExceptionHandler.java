package com.example.demo;

import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.domain.entities.dtos.ErrorMessage;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({ BadRequestException.class, InvalidDataException.class
	})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(Exception exception) {
        return new ErrorMessage("Invalid data", exception.getMessage());
    }
    
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage methodArgumentNotValid(MethodArgumentNotValidException exception) {
		StringBuilder sb = new StringBuilder("ERRORES:");
		exception.getBindingResult().getFieldErrors().stream()
				.forEach(err -> sb.append(" " + err.getField() + ": " + err.getDefaultMessage()));
		return new ErrorMessage("Invalid data", sb.toString());
	}
}

