package com.alkham.libros.services.impl;

import com.alkham.libros.domain.entities.User;
import com.alkham.libros.domain.repositories.UserRepository;
import com.alkham.libros.resources.dto.CredentialsDTO;
import com.alkham.libros.services.exceptions.DatabaseException;
import com.alkham.libros.services.exceptions.InvalidCredentialsException;
import com.alkham.libros.services.interfaces.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override public User insert(User entity) {
       boolean isPresentEmail = repository.findByUsernameOrEmail(entity.getEmail()).isPresent();
       if (isPresentEmail) throw new DatabaseException("Este email já está em uso!");
       try {
            String encryptedPassword = encoder.encode(entity.getPassword());
            entity.setPassword(encryptedPassword);
            return repository.save(entity);
        } catch (DataIntegrityViolationException ex) {
           throw new DatabaseException("Esse username já esta em uso. Escolha outro.");
        }
    }

    @Override public void delete(Long id) {}

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new InvalidCredentialsException("Usuário não encontrado."));
    }

    @Override public User authenticate(CredentialsDTO credentials) {
        User user = (User) loadUserByUsername(credentials.getUsername());
        boolean isValid = encoder.matches(credentials.getPassword(), user.getPassword());
        if (isValid) return user;
        throw new InvalidCredentialsException("Usuário e/ou senha inválidos!");
    }
}
