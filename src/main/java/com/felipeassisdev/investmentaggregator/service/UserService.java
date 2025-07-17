package com.felipeassisdev.investmentaggregator.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.felipeassisdev.investmentaggregator.controller.UpdateUserDto;
import org.springframework.stereotype.Service;

import com.felipeassisdev.investmentaggregator.entity.User;
import com.felipeassisdev.investmentaggregator.controller.CreateUserDto;
import com.felipeassisdev.investmentaggregator.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID create(CreateUserDto createUserDto) {
        var userEntity = new User(
                null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                null,
                null
        );
        var usedSaved = userRepository.save(userEntity);
        return usedSaved.getId();
    }

    public Optional<User> getById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(String id) {
        var userId = UUID.fromString(id);

        var userExists = userRepository.existsById(userId);

        if (userExists) {
            userRepository.deleteById(userId);
        }
    }

    public Optional<User> updateById(String userId, UpdateUserDto updateUserDto) {
        var userById = userRepository.findById(UUID.fromString(userId));
        if (userById.isPresent()) {
            var currentUser = userById.get();
            if (updateUserDto.username() != null) {
                currentUser.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                currentUser.setPassword(updateUserDto.password());
            }
            userRepository.save(currentUser);
            return Optional.of(currentUser);
        }
        return Optional.empty();
    }
}
