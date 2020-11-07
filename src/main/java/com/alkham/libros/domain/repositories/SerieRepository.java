package com.alkham.libros.domain.repositories;
import com.alkham.libros.domain.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository  extends JpaRepository<Serie, Long> {
}
