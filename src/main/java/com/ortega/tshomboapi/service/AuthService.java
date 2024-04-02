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
import org.apache.coyote.Response;
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
        return ResponseHandler.response("", HttpStatus.OK, null);
    }

    @Override
    public ResponseEntity<Object> register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseHandler.response("Email is already taken", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseHandler.response("Username is already taken", HttpStatus.BAD_REQUEST, null);
        }

        // find a role by name
        Optional<Role> role = roleRepository.findRoleByName(registerDto.getRoleName());
        User user = new User();

        // if a role is present
        if (role.isPresent()) {
            user = User.builder()
                    .username(registerDto.getUsername())
                    .email(registerDto.getPassword())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .role(role.get())
                    .build();
        }

        return ResponseHandler.response("User created successfully", HttpStatus.OK, user);
    }
}
