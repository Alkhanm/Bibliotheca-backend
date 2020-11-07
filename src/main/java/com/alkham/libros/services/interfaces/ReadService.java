package com.alkham.libros.services.interfaces;
import com.alkham.libros.domain.entities.Read;

public interface ReadService {
    Read update(Long id, Read read);
}
