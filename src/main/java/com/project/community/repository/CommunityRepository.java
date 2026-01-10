package com.project.community.repository;

import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Integer> {
    Optional<Community> findByCommunityNameAndCityAndZip(String communityName,String city,String zip);
    List<Community> findByCommunityType(CommunityType communityType);
}
