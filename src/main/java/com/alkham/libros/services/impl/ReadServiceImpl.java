package com.alkham.libros.services.impl;
import com.alkham.libros.domain.entities.Read;
import com.alkham.libros.domain.repositories.ReadRepository;
import com.alkham.libros.services.interfaces.ReadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadServiceImpl implements ReadService {
    private final ReadRepository repository;

    public ReadServiceImpl(ReadRepository repository) {
        this.repository = repository;
    }


    @Transactional
    @Override public Read update(Long id, Read entity) {
        return repository.save(entity);
    }
}
