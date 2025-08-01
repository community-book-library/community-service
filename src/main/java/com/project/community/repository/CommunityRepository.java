package com.project.community.repository;

import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Short> {
    List<Community> findByCommunityType(CommunityType communityType);
}
