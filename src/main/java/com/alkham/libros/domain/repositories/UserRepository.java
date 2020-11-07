package com.alkham.libros.domain.repositories;
import com.alkham.libros.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username LIKE ?1 OR u.email LIKE ?1")
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);
}
