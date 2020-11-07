package com.alkham.libros.resources.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CredentialsDTO {
    private String username;
    private String password;
    private String email;

    public CredentialsDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
