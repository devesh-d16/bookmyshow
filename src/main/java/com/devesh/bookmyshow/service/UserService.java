package com.devesh.bookmyshow.service;

import com.devesh.bookmyshow.dto.UserDTO;
import com.devesh.bookmyshow.entity.User;
import com.devesh.bookmyshow.exceptions.InvalidRequestException;
import com.devesh.bookmyshow.exceptions.ResourceNotFoundException;
import com.devesh.bookmyshow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User registerUser(UserDTO userDTO) {
        if (userDTO.getUserName() == null || userDTO.getUserName().isBlank()) {
            throw new InvalidRequestException("User name cannot be blank.");
        }

        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        User user = userRepository.getUsersByUserId(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID " + userId);
        }
        return user;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }
}
