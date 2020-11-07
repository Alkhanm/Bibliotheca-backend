package com.alkham.libros.domain.repositories;

import com.alkham.libros.domain.entities.ReadingList;
import com.alkham.libros.domain.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    @EntityGraph(attributePaths = {"user", "categories"})
    Set<ReadingList> findByUser(User user);
}
