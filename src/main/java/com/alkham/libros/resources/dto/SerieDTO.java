package com.alkham.libros.resources.dto;
import com.alkham.libros.domain.entities.Serie;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data @NoArgsConstructor
public class SerieDTO {
    private Long id;
    private String name;

    public SerieDTO(Serie serie) {
       BeanUtils.copyProperties(serie, this);
    }
}
