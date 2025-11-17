package com.example.securityJwt.repository;


import com.example.securityJwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository {
    Optional<Role> findByName(String name);
}
