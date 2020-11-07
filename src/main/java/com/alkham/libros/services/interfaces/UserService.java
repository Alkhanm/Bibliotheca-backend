package com.alkham.libros.services.interfaces;

import com.alkham.libros.domain.entities.User;
import com.alkham.libros.resources.dto.CredentialsDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User authenticate(CredentialsDTO credentials);
    User insert(User user);
    void delete(Long id);
}
