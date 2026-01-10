package com.project.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private String communityName;
    private String communityType;
    private String city;
    private String state;
    private String zip;
    private String zip4;
}
