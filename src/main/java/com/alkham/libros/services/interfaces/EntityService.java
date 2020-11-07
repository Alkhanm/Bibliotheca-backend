package com.alkham.libros.services.interfaces;

public interface EntityService<T> {
    T findById(Long id);
    T insert(T t);
    void delete(Long id);
}
