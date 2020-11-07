package com.alkham.libros.resources;

import com.alkham.libros.domain.entities.ReadingList;
import com.alkham.libros.resources.dto.ReadingListDTO;
import com.alkham.libros.services.interfaces.ReadingListService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lists")
public class ReadingListResources {
    private final ReadingListService service;
    public ReadingListResources(ReadingListService service) {
        this.service = service;
    }

    @GetMapping
    public Set<ReadingListDTO> searchByUser(){
        return service.findByUser().stream()
                .map(l -> new ReadingListDTO(l)).collect(Collectors.toSet());
    }

    @PostMapping
    public ReadingListDTO save(@RequestBody ReadingList readingList){
        return new ReadingListDTO(service.insert(readingList));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
