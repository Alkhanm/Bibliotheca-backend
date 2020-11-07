package com.alkham.libros.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Entity @Table(name = "tb_authors")
@NoArgsConstructor  @Getter
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Setter private String name;

    @ManyToOne
    private ReadingList readingList;

    @OneToMany(orphanRemoval = true, mappedBy = "author")
    @JsonIgnore private Set<Book> books;

    /** Dois Author's -> são "iguais" se possuieram o mesmo 'id' ou o mesmo 'title'.
     * **/
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        if (id != null && id.equals(author.id)) return true;
        if (name.equalsIgnoreCase(author.name)) return true;
        return false;
    }
    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
