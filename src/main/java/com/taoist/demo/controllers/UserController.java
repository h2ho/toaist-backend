package com.taoist.demo.controllers;

import com.taoist.demo.dto.ErrorResponse;
import com.taoist.demo.dto.LoginRequest;
import com.taoist.demo.dto.UserResponse;
import com.taoist.demo.entities.User;
import com.taoist.demo.services.UserService;
import com.taoist.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Mapper mapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        User found = userService.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (found == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.getErrors().add("not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(mapper.createUserResponse(found));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@RequestParam UUID id) {
        User found = userService.findUserById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(found.getId().toString());
        return ResponseEntity.ok(userResponse);
    }

}
