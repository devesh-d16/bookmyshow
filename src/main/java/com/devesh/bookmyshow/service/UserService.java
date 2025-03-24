package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.entity.User;
import com.devesh.bookmyshow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
