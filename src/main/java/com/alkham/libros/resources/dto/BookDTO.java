package com.alkham.libros.resources.dto;

import com.alkham.libros.domain.entities.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Data @NoArgsConstructor
public class BookDTO {

    @Setter private Long id;
    @Setter private String title;
    @Setter private String path;

    private ReadDTO read;
    private AuthorDTO author;
    private SerieDTO serie;

    public BookDTO(Book book) {
        BeanUtils.copyProperties(book, this);
        this.read = new ReadDTO(book.getRead());
        this.author = new AuthorDTO(book.getAuthor());
        this.serie = book.getSerie() != null ? new SerieDTO(book.getSerie()) : null;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO dto = new BookDTO((Book) o);
        if (id != null && id.equals(dto.id)) return true;
        if (title.equalsIgnoreCase(dto.title)) return true;
        return false;
    }

}