package com.dvc.identity.controller;

import com.dvc.identity.model.jpa.User;
import com.dvc.identity.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/get")
    public ResponseEntity<List<User>> show() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> showById(@PathVariable("id") long id) {
        Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty()) throw new IllegalArgumentException("No such user");
        return ResponseEntity.ok(maybeUser.get());
    };

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/u")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/m")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/a")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}