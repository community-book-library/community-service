package com.project.community.repository;


import com.project.community.model.UserCommunityRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommUserRoleRepository extends JpaRepository<UserCommunityRole,Integer> {
    Optional<UserCommunityRole> findByCommunityIdAndRoleId(int communityId, int roleId);

}
