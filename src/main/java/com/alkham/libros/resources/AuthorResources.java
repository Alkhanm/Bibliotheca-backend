package com.alkham.libros.resources;
import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.ReadingList;
import com.alkham.libros.resources.dto.AuthorDTO;
import com.alkham.libros.services.interfaces.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorResources {
    private final AuthorService service;
    public AuthorResources(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public Set<AuthorDTO> findByList(@RequestParam ReadingList readingList){
        return service.findByReadingList(readingList).stream()
                .map(a -> new AuthorDTO(a)).collect(Collectors.toSet());
    }

    @PostMapping
    public AuthorDTO save(@RequestBody Author author){
        return new AuthorDTO(service.insert(author));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
