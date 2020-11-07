package com.alkham.libros.resources.dto;

import com.alkham.libros.domain.entities.Author;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data @NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;

    private ReadingListDTO readingList;

    public AuthorDTO(Author author) {
        BeanUtils.copyProperties(author, this);
        this.readingList = new ReadingListDTO(author.getReadingList());
    }
}
