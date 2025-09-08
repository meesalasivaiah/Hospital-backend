package com.hospital.service;

import com.hospital.config.JwtUtil;
import com.hospital.model.User;
import com.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register new user
    public User register(User user) {
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Login method returning Optional JWT
    public Optional<String> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Validate password
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate JWT token
                String token = jwtUtil.generateToken(user.getUsername());
                return Optional.of(token);
            }
        }

        // Wrong username or password
        return Optional.empty();
    }
}
