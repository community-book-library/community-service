package com.project.community.service;

import com.project.community.DTO.CommunityDTO;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    public Community save(CommunityDTO communityDTO){
        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }
        community.setCity(communityDTO.getCity());
        community.setState(communityDTO.getState());
        community.setZip(communityDTO.getZip());
        community.setZip4(communityDTO.getZip4());
        community.setCreatedBy(applicationName);

        LocalDateTime dt = LocalDateTime.now();
        community.setCreatedTimestamp(dt);
        Community response = communityRepository.save(community);
        return response;
    }

    public Community update(int id, CommunityDTO communityDTO){
        Community community = communityRepository.findById(id).get();
        community.setCommunityName(communityDTO.getCommunityName());
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }
        community.setCity(communityDTO.getCity());
        community.setState(communityDTO.getState());
        community.setZip(communityDTO.getZip());
        community.setZip4(communityDTO.getZip4());
        community.setUpdatedBy(applicationName);
        LocalDateTime dt = LocalDateTime.now();
        community.setUpdatedTimestamp(dt);
        Community response = communityRepository.save(community);
        return response;
    }

    public List<Community> findByCommunityType(CommunityType communityType){
        List<Community> communities =  communityRepository.findByCommunityType(communityType);
        return communities;
    }

    public Community findByCommunityId(int communityId){
        if(communityRepository.findById(communityId).isPresent()){
            Community community = communityRepository.findById(communityId).get();
            return community;
        }
        return null;
    }

    public void deleteByCommunityId(int communityId) {
        communityRepository.deleteById(communityId);
    }
}
