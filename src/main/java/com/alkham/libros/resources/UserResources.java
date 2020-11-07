package com.alkham.libros.resources;
import com.alkham.libros.domain.entities.User;
import com.alkham.libros.resources.dto.CredentialsDTO;
import com.alkham.libros.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserResources {
    private final UserService service;
    public UserResources(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CredentialsDTO credentials){
        service.insert(new User(credentials));
    }
}
