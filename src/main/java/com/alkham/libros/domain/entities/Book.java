package com.alkham.libros.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity @Table(name = "tb_books")
@NoArgsConstructor @Getter
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Setter private String title;

    @Setter private String path;

    @OneToOne(cascade = { PERSIST, REMOVE }, orphanRemoval = true)
    private Read read;

    @ManyToOne
    @Setter private Author author;

    @ManyToOne(cascade = { PERSIST })
    @Setter private Serie serie;


    /** Dois Book's -> são "iguais" se possuieram o mesmo 'id' ou o mesmo 'title'. **/
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        if (id != null && id.equals(book.id)) return true;
        if (title.equalsIgnoreCase(book.title)) return true;
        return false;
    }
    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
