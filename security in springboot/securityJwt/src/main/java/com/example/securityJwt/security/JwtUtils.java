package com.example.securityJwt.security;


import com.example.securityJwt.model.Role;
import com.example.securityJwt.model.User;
import com.example.securityJwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;


import javax.crypto.SecretKey;


@Component
public class JwtUtils {
    //secretKey
    private final static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //expiration Time
    private final int jwtExpirationMs = 8640000;

    private static  JwtDecoder decoder=null;

    private UserRepository userRepository;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //generateToken
    public String generateToken(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Set<Role> rolse = user.get().getRoles();

        return Jwts.builder().setSubject(username).claim("roles", rolse.stream().
                map(role -> role.getName()).collect(Collectors.joining(","))).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMs)).signWith(secretKey).compact();
    }

    //extract User Name
    public static String extractUsername(String token) {
        JwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
        Jwt jwt = decoder.decode(token);
        return jwt.getSubject(); // same as getBody().getSubject()
    }

    //get roles
    public static Set<String> extractRoles(String token) {
        JwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
        Jwt jwt = decoder.decode(token);
        Object rolesObj = jwt.getClaims().get("roles");

        if (rolesObj instanceof List<?>) {
            return ((List<?>) rolesObj).stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    public static boolean isTokenValid(String token) {
        JwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).build();

        try {
            Jwt jwt = decoder.decode(token);
            String username = jwt.getSubject();
            Date expiration = Date.from(jwt.getExpiresAt());

            return username != null && expiration != null && expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

