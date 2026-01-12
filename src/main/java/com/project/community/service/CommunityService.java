package com.project.community.service;

import com.project.community.common.library.entity.Roles;
import com.project.community.dto.CommunityDTO;
import com.project.community.dto.ManagerDTO;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.model.RegisterToken;
import com.project.community.repository.CommRoleRepository;
import com.project.community.repository.CommunityRepository;
import com.project.community.repository.RegisterTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommRoleRepository commRoleRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegisterTokenRepository registerTokenRepository;

    public Community save(CommunityDTO communityDTO){
        //Arun: Extract below logic to a method, same code used in update
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
        //Arun: Extract below logic to a method, same code used in save
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

    public Optional<Community> checkCommunity(CommunityDTO communityDTO) {
        Optional<Community> response =  communityRepository.findByCommunityNameAndCityAndZip(communityDTO.getCommunityName(),communityDTO.getCity(),communityDTO.getZip());
        return response;
    }

    public String generateManagerRegisterURL(ManagerDTO managerDTO, String token) {
        //Arun: this hashed is not used?
        String hashed = passwordEncoder.encode(token);

        //Arun: create a findByRoleName query in repository instead of using findAll
        List<Roles> roles = commRoleRepository.findAll();
        Roles role = roles.stream().filter(rl -> rl.getRole().toUpperCase().equals(managerDTO.getAssignRole().toUpperCase())).findFirst().get();
        RegisterToken reg = new RegisterToken();
        reg.setCommunityId(managerDTO.getCommunityId());
        reg.setUsername(managerDTO.getEmail());
        reg.setToken(token);
        reg.setStatus("ACTIVE");
        reg.setRoleId(role.getRoleId());
        registerTokenRepository.save(reg);
        String registerLink = "http://localhost:8080/register/" + token;
        return registerLink;
    }
}
