package com.project.community.repository;

import com.project.community.common.library.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommRoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRole(String role);
}

