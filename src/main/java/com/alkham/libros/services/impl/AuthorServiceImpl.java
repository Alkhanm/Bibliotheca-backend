package com.alkham.libros.services.impl;
import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.ReadingList;
import com.alkham.libros.domain.repositories.AuthorRepository;
import com.alkham.libros.services.exceptions.ResourceAlreadyExistsException;
import com.alkham.libros.services.exceptions.ResourceNotFoundException;
import com.alkham.libros.services.interfaces.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override public Author findById(Long id) {
        return repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));
    }

    @Transactional(readOnly = true)
    @Override public Set<Author> findByReadingList(ReadingList readingList) {
        return repository.findByReadingList(readingList);
    }

    @Transactional
    @Override public Author insert(Author author) {
       Boolean exists = findByReadingList(author.getReadingList()).stream().anyMatch(author::equals);
       if (!exists) return repository.save(author);
       throw new ResourceAlreadyExistsException("Esse author já está salvo nesta lista!");
    }

    @Transactional
    @Override public void delete(Long id) {
        repository.deleteById(id);
    }

}
