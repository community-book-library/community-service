package com.project.community.repository;

import com.project.community.model.CommManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommMgrRepository extends JpaRepository<CommManager,Integer> {
}
