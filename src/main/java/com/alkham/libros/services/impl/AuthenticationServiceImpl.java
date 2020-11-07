package com.alkham.libros.services.impl;
import com.alkham.libros.services.interfaces.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public Authentication getAuthentication() {
       return SecurityContextHolder.getContext().getAuthentication();
    }
}
