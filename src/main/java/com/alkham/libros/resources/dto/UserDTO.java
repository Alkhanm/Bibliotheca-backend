package com.alkham.libros.resources.dto;

import com.alkham.libros.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data @NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String token;

    public  UserDTO(User user){
        BeanUtils.copyProperties(user, this);
    }
    public UserDTO(User user, String token) {
        BeanUtils.copyProperties(user, this);
        this.token = token;
    }

}
