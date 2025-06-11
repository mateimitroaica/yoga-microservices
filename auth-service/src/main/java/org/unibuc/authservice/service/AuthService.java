package org.unibuc.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibuc.authservice.dto.AuthRequest;
import org.unibuc.authservice.dto.AuthResponse;
import org.unibuc.authservice.dto.RegisterRequest;
import org.unibuc.authservice.entity.Role;
import org.unibuc.authservice.entity.User;
import org.unibuc.authservice.repository.UserRepository;
import org.unibuc.authservice.security.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, JwtUserDetailsService userDetailsService,
                       TokenBlacklistService tokenBlacklistService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public void logout(String token) {
        tokenBlacklistService.blacklistToken(token);
    }
}
