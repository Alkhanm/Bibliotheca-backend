package com.alkham.libros.domain.repositories;

import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.ReadingList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = {"readingList", "readingList.categories"})
    Set<Author> findByReadingList(ReadingList readingList);
}
