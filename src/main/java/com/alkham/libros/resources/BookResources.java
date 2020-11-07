package com.alkham.libros.resources;

import com.alkham.libros.domain.entities.Author;
import com.alkham.libros.domain.entities.Book;
import com.alkham.libros.resources.dto.BookDTO;
import com.alkham.libros.services.interfaces.BookService;
import com.alkham.libros.services.interfaces.ReadService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
@RestController
@RequestMapping("/api/books")
public class BookResources {
    private final BookService service;
    private final ReadService readService;

    public BookResources(BookService service, ReadService readService) {
        this.service = service;
        this.readService = readService;
    }

    @GetMapping
    public Set<BookDTO> listByAuthor(@RequestParam Author author){
        return service.findByAuthor(author).stream()
                .map(b -> new BookDTO(b)).collect(Collectors.toSet());
    }

    @GetMapping("/last")
    public BookDTO findLatestBook(){
        return new BookDTO(service.findLastBook());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public BookDTO save(@RequestBody Book book){
        return new BookDTO(service.insert(book));
    }

    @PutMapping("/{id}")
    public BookDTO update(@RequestBody Book entity){
        return new BookDTO(service.update(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }



}
