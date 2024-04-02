package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.dto.AuthDto;
import com.ortega.tshomboapi.dto.RegisterDto;
import com.ortega.tshomboapi.model.Role;
import com.ortega.tshomboapi.model.User;
import com.ortega.tshomboapi.repository.RoleRepository;
import com.ortega.tshomboapi.repository.UserRepository;
import com.ortega.tshomboapi.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<Object> authentication(AuthDto authDto) {
        Optional<User> user = userRepository.findUserByEmail(authDto.getEmail());
        if (user.isPresent()) {
            if (passwordEncoder.matches(authDto.getPassword(), user.get().getPassword())) {
                return ResponseHandler.response("Authentication success", HttpStatus.OK, user);
            }
        }

        return ResponseHandler.response("Authentication Error", HttpStatus.BAD_REQUEST, null);
    }

    @Override
    public ResponseEntity<Object> register(RegisterDto registerDto) {
        if (userRepository.findUserByEmail(registerDto.getEmail()).isPresent()) {
            return ResponseHandler.response("Email is already taken", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.findUserByUsername(registerDto.getUsername()).isPresent()) {
            return ResponseHandler.response("Username is already taken", HttpStatus.BAD_REQUEST, null);
        }

        // find a role by name
        Role role = roleRepository.findRoleByName(registerDto.getRole());
        User user = User.builder()
                    .username(registerDto.getUsername())
                    .email(registerDto.getEmail())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .role(role)
                    .build();

        userRepository.save(user);
        return ResponseHandler.response("User created successfully", HttpStatus.OK, user);
    }
}
