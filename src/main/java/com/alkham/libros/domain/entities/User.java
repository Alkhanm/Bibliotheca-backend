package com.alkham.libros.domain.entities;

import com.alkham.libros.resources.dto.CredentialsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity @Table(name = "tb_users")
@NoArgsConstructor @Getter @ToString
public class User implements UserDetails, Cloneable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O nome de usuário não pode estar vazio.")

    private String username;

    @JsonIgnore
    @Setter private String password;

    @Column(unique = true)
    @Email(message = "O email deve ser válido")

    private String email;

    public User(CredentialsDTO credentials) {
        this.username = credentials.getUsername();
        this.email = credentials.getEmail();
        this.password = credentials.getPassword();
    }
    @JsonIgnore @Override public Collection<? extends GrantedAuthority> getAuthorities() {  return null;}
    @JsonIgnore @Override public boolean isAccountNonExpired() { return true; }
    @JsonIgnore @Override public boolean isAccountNonLocked() { return true; }
    @JsonIgnore @Override public boolean isCredentialsNonExpired() { return true; }
    @JsonIgnore @Override public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (id != null && id.equals(user.id)) return true;
        if (username.equalsIgnoreCase(user.username) || email.equalsIgnoreCase(user.email)) return true;
        return false;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
