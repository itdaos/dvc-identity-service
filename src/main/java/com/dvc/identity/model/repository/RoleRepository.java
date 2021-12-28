package com.dvc.identity.model.repository;

import com.dvc.identity.model.jpa.ERole;
import com.dvc.identity.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
