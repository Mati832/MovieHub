package org.example.moviehub.repository;


import org.example.moviehub.enums.RoleType;
import org.example.moviehub.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
