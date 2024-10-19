package com.taoist.demo.services;

import com.taoist.demo.entities.Inventory;
import com.taoist.demo.entities.User;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User findUserById(UUID uuid) {
        Optional<User> optionalUser = repository.findById(uuid);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        Optional<User> optionalUser = repository.findByUsernameAndPassword(username, password);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
    }
}
