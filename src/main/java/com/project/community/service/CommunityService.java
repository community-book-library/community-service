package com.project.community.service;

import com.project.community.controller.CommunityDTO;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    public CommunityDTO save(CommunityDTO communityDTO){
        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }

        community.setCommunityManagerId(communityDTO.getCommunityManagerId());
        community.setCity(communityDTO.getCity());
        community.setState(communityDTO.getState());
        community.setActiveIndicator('Y');
        if (!communityDTO.isActiveIndicator()) {
            community.setActiveIndicator('N');
        }
        community.setZip(communityDTO.getZip());
        community.setZip4(communityDTO.getZip4());
        community.setCreatedBy(applicationName);

        Date curr = new Date();
        Timestamp created = new Timestamp(curr.getTime());
        community.setCreatedTimestamp(created);
        Community createdResponse = communityRepository.save(community);
        communityDTO.setCommunityId(createdResponse.getCommunityId());
        return communityDTO;
    }

    public CommunityDTO update(CommunityDTO communityDTO){
        Community community = communityRepository.findById(communityDTO.getCommunityId()).get();
        community.setCommunityName(communityDTO.getCommunityName());
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }

        community.setCommunityManagerId(communityDTO.getCommunityManagerId());
        community.setCity(communityDTO.getCity());
        community.setState(communityDTO.getState());
        community.setActiveIndicator('Y');
        if (!communityDTO.isActiveIndicator()) {
            community.setActiveIndicator('N');
        }
        community.setZip(communityDTO.getZip());
        community.setZip4(communityDTO.getZip4());
        community.setUpdatedBy(applicationName);
        Date curr = new Date();
        Timestamp updated = new Timestamp(curr.getTime());
        community.setUpdatedTimestamp(updated);
        communityRepository.save(community);
        return communityDTO;
    }

    public List<CommunityDTO> findByCommunityType(CommunityType communityType){
        List<Community> communities =  communityRepository.findByCommunityType(communityType);
        List<CommunityDTO> communityDTOS = new ArrayList<>();
        if(!communities.isEmpty()){
            for(Community community : communities){
                CommunityDTO communityDTO = new CommunityDTO();
                communityDTO.setCommunityId(community.getCommunityId());
                communityDTO.setCommunityName(community.getCommunityName());
                communityDTO.setCommunityType(community.getCommunityType().toString());
                communityDTO.setCommunityManagerId(community.getCommunityManagerId());
                communityDTO.setCity(community.getCity());
                communityDTO.setState(community.getState());
                communityDTO.setActiveIndicator(true);
                if(community.getActiveIndicator() == 'N'){
                    communityDTO.setActiveIndicator(false);
                }
                communityDTO.setZip(community.getZip());
                communityDTO.setZip4(community.getZip4());
                communityDTOS.add(communityDTO);
            }
        }
        return communityDTOS;
    }

    public CommunityDTO findByCommunityId(short communityId){
        if(communityRepository.findById(communityId).isPresent()){
            Community community = communityRepository.findById(communityId).get();
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setCommunityId(community.getCommunityId());
            communityDTO.setCommunityName(community.getCommunityName());
            communityDTO.setCommunityType(community.getCommunityType().toString());
            communityDTO.setCommunityManagerId(community.getCommunityManagerId());
            communityDTO.setCity(community.getCity());
            communityDTO.setState(community.getState());
            communityDTO.setActiveIndicator(true);
            if(community.getActiveIndicator() == 'N'){
                communityDTO.setActiveIndicator(false);
            }
            communityDTO.setZip(community.getZip());
            communityDTO.setZip4(community.getZip4());
            return communityDTO;
        }
        return null;

    }

    public void deleteByCommunityId(short communityId) {
        communityRepository.deleteById(communityId);
    }
}
