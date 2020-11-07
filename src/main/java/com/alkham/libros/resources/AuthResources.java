package com.alkham.libros.resources;
import com.alkham.libros.domain.entities.User;
import com.alkham.libros.resources.dto.CredentialsDTO;
import com.alkham.libros.resources.dto.UserDTO;
import com.alkham.libros.services.JwtService;
import com.alkham.libros.services.interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResources {
    private final JwtService jwtService;
    private final UserService userService;

    public AuthResources(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public UserDTO signIn(@RequestBody CredentialsDTO credentials) {
        User user = userService.authenticate(credentials);
        String token = jwtService.generateToken(user);
        return new UserDTO(user, token);
    }
    @PostMapping("/validate-token")
    public boolean validate(@RequestBody String token){
        return jwtService.validateToken(token);
    }
}
