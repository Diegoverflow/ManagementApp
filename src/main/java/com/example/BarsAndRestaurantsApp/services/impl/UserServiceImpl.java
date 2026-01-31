package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.entities.UserEntity;
import com.example.BarsAndRestaurantsApp.repositories.UserRepository;
import com.example.BarsAndRestaurantsApp.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<UserEntity> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean exits(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public UserEntity partialUpdate(UUID id, UserEntity user) {
        user.setId(id);
        return repository.findById(id).map(
                existingUser ->{
                    Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);
                    Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(user.getRole()).ifPresent(existingUser::setRole);
                    return repository.save(existingUser);
                }
        ).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
