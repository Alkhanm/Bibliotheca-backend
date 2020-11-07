package com.alkham.libros.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity @Table(name = "tb_serie")
@NoArgsConstructor @Getter
public class Serie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "serie", cascade = { PERSIST, REMOVE })
    private Set<Book> books = new HashSet<>();

}
