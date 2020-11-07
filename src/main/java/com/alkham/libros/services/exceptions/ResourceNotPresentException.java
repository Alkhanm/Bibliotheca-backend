package com.alkham.libros.services.exceptions;

public class ResourceNotPresentException extends RuntimeException{
    public ResourceNotPresentException(String msg){
        super(msg);
    }
}
