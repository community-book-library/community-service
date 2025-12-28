package com.project.community.controller;

import com.project.community.DTO.CommunityDTO;
import com.project.community.common.library.dto.UserDTO;
import com.project.community.common.library.entity.Users;
import com.project.community.common.library.service.CommUserDetails;
import com.project.community.model.CommManager;
import com.project.community.model.Community;
import com.project.community.model.CommunityType;
import com.project.community.repository.CommMgrRepository;
import com.project.community.service.APIService;
import com.project.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private APIService apiService;

    @Autowired
    private CommMgrRepository commMgrRepository;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody CommunityDTO communityDTO) {
        Community createdResponse = communityService.save(communityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse.getId());
    }

    @PostMapping(value = "/{comm_id}/manager", consumes = "application/json")
    public ResponseEntity<?> save(@PathVariable("comm_id") int comm_id ,@RequestBody UserDTO userDTO) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return ResponseEntity.badRequest().body("Invalid JWT Token");
        }

        CommUserDetails userDetails = (CommUserDetails) auth.getPrincipal();
        Users user = userDetails.getUser();
        String role = user.getRoles().getRole();
        if(!role.toUpperCase().equals("ADMIN")){
            return ResponseEntity.badRequest().body("Not authorized to create user");
        }
        Users resp = apiService.callPostApi(userDTO);
        if(resp == null){
            return ResponseEntity.badRequest().body("Manager to community association failed");
        }
        int manager_id = resp.getId();
        CommManager commManager = new CommManager();
        commManager.setCommunityId(comm_id);
        commManager.setManagerId(manager_id);
        commMgrRepository.save(commManager);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp.getId());

    }

//    @PostMapping(value = "/{comm_id}/invite", consumes = "application/json")
//    public ResponseEntity<?> sendInvite(@PathVariable("comm_id") int id, @RequestBody List<String> emails){
//
//    }

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
