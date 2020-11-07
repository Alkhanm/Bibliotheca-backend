package com.alkham.libros.domain.repositories;
import com.alkham.libros.domain.entities.Read;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadRepository extends JpaRepository<Read, Long> {
}
