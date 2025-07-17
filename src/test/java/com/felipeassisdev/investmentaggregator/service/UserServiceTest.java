package com.felipeassisdev.investmentaggregator.service;

import java.util.Optional;
import java.util.UUID;
import java.time.Instant;

import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

import com.felipeassisdev.investmentaggregator.entity.User;
import com.felipeassisdev.investmentaggregator.controller.CreateUserDto;
import com.felipeassisdev.investmentaggregator.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Captor
    private ArgumentCaptor<UUID> UUIDCaptor;

    @Nested
    class create {
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateUser() {
            // Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "1234",
                    Instant.now(),
                    null
            );
            doReturn(user)
                    .when(userRepository)
                    .save(userCaptor.capture());
            var input = new CreateUserDto("username", "email@email.com", "123");
            // Act
            var output = userService.create(input);
            // Assert
            assertNotNull(output);
            assertEquals(input.email(), userCaptor.getValue().getEmail());
            assertEquals(input.username(), userCaptor.getValue().getUsername());
            assertEquals(input.password(), userCaptor.getValue().getPassword());
        }


        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {
            // Arrange
            doThrow(new RuntimeException())
                    .when(userRepository).save(any());
            var input = new CreateUserDto("username", "email@email.com", "123");
            // Act
            assertThrows(RuntimeException.class, () -> userService.create(input));
        }
    }

    @Nested
    class getById {
        @Test
        @DisplayName("Should return a user by id with success when Optional is present")
        void shouldReturnUserByIdWhenOptionalIsPresent() {
            // Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "1234",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(UUIDCaptor.capture());
            // Act
            var output = userService.getById(user.getId().toString());
            // Assert
            assertTrue(output.isPresent());
            assertEquals(user.getId(), UUIDCaptor.getValue());
        }

        @Test
        @DisplayName("Should return a user by id with success when Optional is empty")
        void shouldReturnUserByIdWhenOptionalIsEmpty() {
            // Arrange
            var userId = UUID.randomUUID();
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(UUIDCaptor.capture());
            // Act
            var output = userService.getById(userId.toString());
            // Assert
            assertTrue(output.isEmpty());
            assertEquals(userId, UUIDCaptor.getValue());
        }
    }
}