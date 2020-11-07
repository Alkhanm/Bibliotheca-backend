package com.alkham.libros.resources.dto;

import com.alkham.libros.domain.entities.ReadingList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Data @NoArgsConstructor
public class ReadingListDTO {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "HH:mm dd/MM/yyyy", timezone = "America/Sao_Paulo" )
    private LocalDateTime startDate;
    private Set<String> categories;

    public ReadingListDTO(ReadingList readingList) {
        BeanUtils.copyProperties(readingList, this);
    }
}
