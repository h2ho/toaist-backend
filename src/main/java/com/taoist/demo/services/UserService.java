package com.taoist.demo.services;

import com.taoist.demo.entities.User;
import com.taoist.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User findUserById(UUID uuid) {
        return repository.findById(uuid).get();
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
}
