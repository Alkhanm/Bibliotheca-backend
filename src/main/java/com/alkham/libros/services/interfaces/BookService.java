package com.alkham.libros.services.interfaces;
import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.Book;

import java.util.Set;

public interface BookService extends EntityService<Book>{
    Set<Book> findByAuthor(Author author);
    Book update(Book entity);
    Book findLastBook();
}
