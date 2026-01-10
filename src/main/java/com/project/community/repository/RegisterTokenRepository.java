package com.project.community.repository;

import com.project.community.model.RegisterToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterTokenRepository extends JpaRepository<RegisterToken,Integer> {
}
