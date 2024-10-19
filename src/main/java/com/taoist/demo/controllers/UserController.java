package com.taoist.demo.controllers;

import com.taoist.demo.dto.ErrorResponse;
import com.taoist.demo.dto.LoginRequest;
import com.taoist.demo.dto.UserResponse;
import com.taoist.demo.entities.User;
import com.taoist.demo.exceptions.BadRequestException;
import com.taoist.demo.exceptions.NotFoundException;
import com.taoist.demo.services.UserService;
import com.taoist.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Mapper mapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        checkRequest(loginRequest);
        User found = userService.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (found == null) {
            throw new NotFoundException("not found");
        }
        return ResponseEntity.ok(mapper.createUserResponse(found));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        if (id == null) {
            throw new BadRequestException(List.of("null id"));
        }
        User found = userService.findUserById(id);
        if (found == null) {
            throw new NotFoundException("not found");
        }
        return ResponseEntity.ok(mapper.createUserResponse(found));
    }

    void checkRequest(LoginRequest loginRequest) {
        List<String> errors = new ArrayList<>();
        if (!StringUtils.hasText(loginRequest.getUsername())) {
            errors.add("Username is empty");
        }
        if (!StringUtils.hasText(loginRequest.getPassword())) {
            errors.add("Password is empty");
        }
        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }
    }

}
