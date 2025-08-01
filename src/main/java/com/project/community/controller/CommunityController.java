package com.project.community.controller;

import com.project.community.model.CommunityType;
import com.project.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping(value = "/community", consumes = "application/json")
    public ResponseEntity<Short> save(@RequestBody CommunityDTO communityDTO) {
        CommunityDTO createdResponse = communityService.save(communityDTO);
        return new ResponseEntity<>(createdResponse.getCommunityId(), HttpStatus.CREATED);
    }


    @GetMapping("/community/{communityId}")
    public ResponseEntity<CommunityDTO> findByCommunityId(@PathVariable("communityId") String communityId){
        short id = Short.parseShort(communityId);
        CommunityDTO communityDTO = communityService.findByCommunityId(id);
        return new ResponseEntity<>(communityDTO,HttpStatus.OK);
    }


    @GetMapping("/community")
    public ResponseEntity<List<CommunityDTO>> findByCommunityType(@RequestParam("communityType") String communityType){
        List<CommunityDTO> commList = switch (communityType.toUpperCase()) {
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
    public ResponseEntity<CommunityDTO> update(@RequestBody CommunityDTO communityDTO){
        CommunityDTO updated = communityService.update(communityDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/community/{communityId}")
    public ResponseEntity<Short> deleteByCommunityId(@PathVariable short communityId){
        communityService.deleteByCommunityId(communityId);
        return new ResponseEntity<>(communityId,HttpStatus.OK);
    }
}
