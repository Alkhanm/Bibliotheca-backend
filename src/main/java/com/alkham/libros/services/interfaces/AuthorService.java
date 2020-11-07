package com.alkham.libros.services.interfaces;
import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.ReadingList;

import java.util.Set;

public interface AuthorService extends EntityService<Author> {
    Set<Author> findByReadingList(ReadingList readingList);
}
