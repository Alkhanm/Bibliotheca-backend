package com.alkham.libros.resources.exceptions;
import com.alkham.libros.services.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public StandardError resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        String error = "Recurso não encontrado.";
        return new StandardError(Instant.now(), NOT_FOUND.value(), error, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(FORBIDDEN)
    public StandardError InvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request){
        String error = "As credenciais informadas não são válidas.";
        return new StandardError(Instant.now(), FORBIDDEN.value(), error, ex.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(BAD_REQUEST)
    public StandardError Database(DatabaseException ex, HttpServletRequest request){
        String error = "Não foi possivel realizar está operação!";
        return new StandardError(Instant.now(), BAD_REQUEST.value(), error, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotPresentException.class)
    @ResponseStatus(NO_CONTENT)
    public StandardError ResourceNotPresent(ResourceNotPresentException ex, HttpServletRequest request){
        String error = "Nada foi encontrado!";
        return new StandardError(Instant.now(), NO_CONTENT.value(), error, ex.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public StandardError ResourceAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request){
        String error = "Esse recurso já existe!";
        return new StandardError(Instant.now(), UNPROCESSABLE_ENTITY.value(), error, ex.getMessage(), request.getRequestURI());
    }
}
