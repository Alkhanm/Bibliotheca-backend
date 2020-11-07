package com.alkham.libros.services.impl;

import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.Book;
import com.alkham.libros.domain.entities.User;
import com.alkham.libros.domain.repositories.BookRepository;
import com.alkham.libros.domain.repositories.SerieRepository;
import com.alkham.libros.services.exceptions.ResourceAlreadyExistsException;
import com.alkham.libros.services.exceptions.ResourceNotFoundException;
import com.alkham.libros.services.exceptions.ResourceNotPresentException;
import com.alkham.libros.services.interfaces.AuthenticationService;
import com.alkham.libros.services.interfaces.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final SerieRepository serieRepository;
    private final AuthenticationService authenticationService;

    public BookServiceImpl(BookRepository repository, SerieRepository serieRepository, AuthenticationService authenticationService) {
        this.repository = repository;
        this.serieRepository = serieRepository;
        this.authenticationService = authenticationService;
    }

    @Transactional(readOnly = true)
    @Override public Book findById(Long id) {
        return repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado!"));
    }

    @Transactional
    @Override public Book insert(Book book) {
        Boolean exists = findByAuthor(book.getAuthor()).stream()
                .anyMatch(book::equals);
        if (exists) throw new ResourceAlreadyExistsException("Esse livro já foi adicionado a este autor/coleção.");
        book.setSerie(book.getSerie().getName() != null ? serieRepository.save(book.getSerie()) : null);
        return repository.save(book);
    }

    @Transactional(readOnly = true)
    @Override public Set<Book> findByAuthor(Author author) {
        return repository.findByAuthor(author);
    }

    @Transactional(readOnly = true)
    @Override public Book findLastBook() {
       User user = (User) authenticationService.getAuthentication().getPrincipal();
       return repository.findBooksByUser(user, PageRequest.of(0, 1))
               .stream().findFirst()
               .orElseThrow(() -> new ResourceNotPresentException("Não há livros recentes"));
    }

    @Transactional
    @Override public Book update(Book entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override public void delete(Long id) {
        repository.deleteById(id);
    }
}
