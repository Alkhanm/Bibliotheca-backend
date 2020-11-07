package com.alkham.libros.services.impl;
import com.alkham.libros.domain.entities.ReadingList;
import com.alkham.libros.domain.entities.User;
import com.alkham.libros.domain.repositories.ReadingListRepository;
import com.alkham.libros.services.exceptions.ResourceNotFoundException;
import com.alkham.libros.services.interfaces.AuthenticationService;
import com.alkham.libros.services.interfaces.ReadingListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service public class ReadingListServiceImpl implements ReadingListService {
    private final ReadingListRepository repository;
    private final AuthenticationService authenticationService;
    public ReadingListServiceImpl(ReadingListRepository repository, AuthenticationService authenticationService) {
        this.repository = repository;
        this.authenticationService = authenticationService;
    }

    @Transactional(readOnly = true)
    @Override public ReadingList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada!"));
    }

    @Transactional(readOnly = true)
    @Override public Set<ReadingList> findByUser() {
        User user = (User) authenticationService.getAuthentication().getPrincipal();
        return repository.findByUser(user);
    }

    @Transactional
    @Override public ReadingList insert(ReadingList readingList) {
        User user = (User) authenticationService.getAuthentication().getPrincipal();
        readingList.setUser(user);
        return repository.save(readingList);
    }

    @Override public void delete(Long id) {
        repository.deleteById(id);
    }


}
