package com.example.securejwt.auth;

import com.example.securejwt.model.Role;
import com.example.securejwt.model.User;
import com.example.securejwt.repo.UserRepository;
import com.example.securejwt.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private  final UserRepository userrepo;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    public AuhtenticateResponse register(RegisterRequest request) {
        var user= User.builder().
                firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userrepo.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuhtenticateResponse.builder().token(jwtToken).build();
    }

    public AuhtenticateResponse authenticate(AuthnticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )

        );
        var user=userrepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuhtenticateResponse.builder().token(jwtToken).build();
    }
}
