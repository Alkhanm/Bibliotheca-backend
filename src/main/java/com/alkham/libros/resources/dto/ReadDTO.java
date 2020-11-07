package com.alkham.libros.resources.dto;
import com.alkham.libros.domain.entities.Read;
import com.alkham.libros.domain.entities.enums.ReadStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class ReadDTO {
    private Long id;
    private Integer page;
    private Integer totalPages;
    private Double zoom;
    private ReadStatus status;
    private LocalDateTime startReading;
    private LocalDateTime lastRead;

    public ReadDTO(Read read) {
        BeanUtils.copyProperties(read, this);
    }
}
