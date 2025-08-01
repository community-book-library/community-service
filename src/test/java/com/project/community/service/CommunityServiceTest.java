package com.project.community.service;

import com.project.community.controller.CommunityDTO;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.repository.CommunityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
class CommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    private static short communityId;

    @Value("${spring.application.name}")
    private String applicationName;

    static
    {
        ++communityId;
    }

    @InjectMocks
    private CommunityService communityService;

    @Test
    void save() {
        CommunityDTO communityDTO = new CommunityDTO((short) 0,"Orleans","TOWNHOUSES",true,
                (short) 1,"Naperville","IL","60565","1001");

        Community community = new Community(communityDTO.getCommunityName(),
                communityDTO.getCommunityManagerId(),communityDTO.getCity(),communityDTO.getState(),communityDTO.getZip(),
                communityDTO.getZip4());
        community.setCommunityId(communityId);
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }
        community.setActiveIndicator('Y');
        if (!communityDTO.isActiveIndicator()) {
            community.setActiveIndicator('N');
        }
        community.setCreatedBy(applicationName);

        Date curr = new Date();
        Timestamp created = new Timestamp(curr.getTime());
        community.setCreatedTimestamp(created);

        when(communityRepository.save(any())).thenReturn(community);
        CommunityDTO saved = communityService.save(communityDTO);
        assertTrue(saved.getCommunityId() > 0);
    }

    @Test
    void update() {
        CommunityDTO communityDTO = new CommunityDTO((short) 1,"Orleans","TOWNHOUSES",true,
                (short) 1,"Naperville","IL","60565","1001");
        Community community = new Community("Orleans",(short)2,"Naperville","IL","60540",
                "1002");
        community.setCommunityId(communityDTO.getCommunityId());
        community.setActiveIndicator('Y');
        community.setCommunityType(CommunityType.APARTMENTS);
        community.setCreatedBy(applicationName);
        community.setCreatedTimestamp(Timestamp.valueOf("2025-01-17 14:25:00"));
        when(communityRepository.findById(anyShort())).thenReturn(Optional.of(community));
        switch (communityDTO.getCommunityType().toUpperCase()) {
            case "APARTMENTS" -> community.setCommunityType(CommunityType.APARTMENTS);
            case "CONDOS" -> community.setCommunityType(CommunityType.CONDOS);
            case "TOWNHOUSES" -> community.setCommunityType(CommunityType.TOWNHOUSES);
            case "SINGLE_FAMILY" -> community.setCommunityType(CommunityType.SINGLE_FAMILY);
        }
        community.setZip(communityDTO.getZip());
        community.setZip4(communityDTO.getZip4());
        community.setUpdatedBy(applicationName);
        Date curr = new Date();
        Timestamp updated = new Timestamp(curr.getTime());
        community.setUpdatedTimestamp(updated);
        when(communityRepository.save(any())).thenReturn(community);
        CommunityDTO updatedResp = communityService.update(communityDTO);
        assertEquals(communityDTO.getZip(), community.getZip());

    }

    @Test
    void findByCommunityType() {
        Community community = new Community("Orleans",(short)2,"Naperville","IL","60540",
                "1002");
        community.setCommunityId((short)1);
        community.setActiveIndicator('Y');
        community.setCommunityType(CommunityType.APARTMENTS);
        community.setCreatedBy(applicationName);
        community.setCreatedTimestamp(Timestamp.valueOf("2025-01-17 14:25:00"));
        when(communityRepository.findByCommunityType(CommunityType.APARTMENTS)).thenReturn(List.of(community));
        List<CommunityDTO> communities = communityService.findByCommunityType(CommunityType.APARTMENTS);
        assertEquals(communities.size() ,1);
    }

    @Test
    void findByCommunityId() {
        short communityId = 1;
        Community community = new Community("Orleans",(short)2,"Naperville","IL","60540",
                "1002");
        community.setCommunityId(communityId);
        community.setActiveIndicator('Y');
        community.setCommunityType(CommunityType.APARTMENTS);
        community.setCreatedBy(applicationName);
        community.setCreatedTimestamp(Timestamp.valueOf("2025-01-17 14:25:00"));
        when(communityRepository.findById(anyShort())).thenReturn(Optional.of(community));
        CommunityDTO response = communityService.findByCommunityId(communityId);
        assertEquals(response.getCommunityId(),communityId);
    }

    @Test
    void deleteByCommunityId() {
        short id = 1;
        communityService.deleteByCommunityId(id);
        verify(communityRepository,times(1)).deleteById(id);
    }
}