package com.alkham.libros.domain.repositories;

import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.Book;
import com.alkham.libros.domain.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findByAuthor(Author author);

    @EntityGraph(attributePaths = { "read", "serie", "author", "author.readingList", "author.readingList.user"})
    @Query("SELECT b FROM Book b " +
           "WHERE b.author.readingList.user = ?1 " +
           "AND b.path IS NOT NULL " +
           "ORDER BY b.read.lastRead DESC")
    List <Book> findBooksByUser(User user, Pageable pageable);
}
