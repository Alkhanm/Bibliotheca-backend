package com.alkham.libros.resources;
import com.alkham.libros.domain.entities.Read;
import com.alkham.libros.resources.dto.ReadDTO;
import com.alkham.libros.services.interfaces.ReadService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/reads")
public class ReadResources {
    private final ReadService service;

    public ReadResources(ReadService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ReadDTO updateReading(@PathVariable Long id, @RequestBody Read entity){
        return new ReadDTO(service.update(id, entity));
    }
}
