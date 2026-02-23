package com.project.community.controller;

import com.project.community.common.library.service.EmailService;
import com.project.community.dto.CommunityDTO;
import com.project.community.dto.ManagerDTO;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.repository.CommMgrRepository;
import com.project.community.service.APIService;
import com.project.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private APIService apiService;

    @Autowired
    private CommMgrRepository commMgrRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDTO communityDTO) {
        // check whether community is already present before adding that to DB
        Optional<Community> alreadyPresent = communityService.checkCommunity(communityDTO);
        if(alreadyPresent.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Community is already registered");
        }
        Community createdResponse = communityService.save(communityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse.getId());
    }

    public static  String generateToken()
    {
        // Generate Unique Token
        UUID uuid = UUID.randomUUID();
        // Returns the UUID as a string (e.g., "550e8400-e29b-41d4-a716-446655440000")
        return uuid.toString();
    }


    @PostMapping(value = "/invite", consumes = "application/json")
    public ResponseEntity<?> sendMailInvite(@RequestBody ManagerDTO managerDTO) {
        if(communityService.checkCommunityManager(managerDTO)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager is already registered");
        }

        String token = generateToken();
        String url = communityService.generateManagerRegisterURL(managerDTO,token);
        emailService.sendInviteEmail(managerDTO.getEmail(),url);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);

    }


    @GetMapping("/community/{communityId}")
    public ResponseEntity<?> findByCommunityId(@PathVariable("communityId") String communityId){
        int id = Integer.parseInt(communityId);
        Community response = communityService.findByCommunityId(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/community")
    public ResponseEntity<?> findByCommunityType(@RequestParam("communityType") String communityType){
        List<Community> commList = switch (communityType.toUpperCase()) {
            case "APARTMENTS" -> communityService.findByCommunityType(CommunityType.APARTMENTS);
            case "TOWNHOUSES" -> communityService.findByCommunityType(CommunityType.TOWNHOUSES);
            case "CONDOS" -> communityService.findByCommunityType(CommunityType.CONDOS);
            case "SINGLE_FAMILY" -> communityService.findByCommunityType(CommunityType.SINGLE_FAMILY);
            default -> throw new IllegalStateException("Unexpected value: " + communityType);
        };

        if(!commList.isEmpty()){
            return new ResponseEntity<>(commList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(commList,HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping(value = "/community" ,consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody int id, CommunityDTO communityDTO){
        Community updated = communityService.update(id, communityDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/community/{communityId}")
    public ResponseEntity<?> deleteByCommunityId(@PathVariable int communityId){
        communityService.deleteByCommunityId(communityId);
        return new ResponseEntity<>(communityId,HttpStatus.OK);
    }
}
