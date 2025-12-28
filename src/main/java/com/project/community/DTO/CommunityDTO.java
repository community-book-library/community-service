package com.project.community.controller;

public class CommunityDTO {
    private short communityId;
    private String communityName;
    private String communityType;
    private boolean activeIndicator;
    private short communityManagerId;
    private String city;
    private String state;
    private String zip;
    private String zip4;

    public CommunityDTO() {
    }

    public CommunityDTO(String communityName, String communityType, boolean activeIndicator, short communityManagerId, String city, String state, String zip, String zip4) {
        this.communityName = communityName;
        this.communityType = communityType;
        this.activeIndicator = activeIndicator;
        this.communityManagerId = communityManagerId;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.zip4 = zip4;
    }

    public CommunityDTO(short communityId, String communityName, String communityType, boolean activeIndicator, short communityManagerId, String city, String state, String zip, String zip4) {
        this.communityId = communityId;
        this.communityName = communityName;
        this.communityType = communityType;
        this.activeIndicator = activeIndicator;
        this.communityManagerId = communityManagerId;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.zip4 = zip4;
    }

    public short getCommunityId() {
        return communityId;
    }

    public void setActiveIndicator(boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    public boolean isActiveIndicator() {
        return activeIndicator;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getCommunityType() {
        return communityType;
    }

    public short getCommunityManagerId() {
        return communityManagerId;
    }

    public void setCommunityId(short communityId) {
        this.communityId = communityId;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setCommunityType(String communityType) {
        this.communityType = communityType;
    }

    public void setCommunityManagerId(short communityManagerId) {
        this.communityManagerId = communityManagerId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip4() {
        return zip4;
    }

    public void setZip4(String zip4) {
        this.zip4 = zip4;
    }

    @Override
    public String toString() {
        return "CommunityDTO{" +
                "communityId=" + communityId +
                ", community_Name='" + communityName + '\'' +
                ", communityType='" + communityType + '\'' +
                ", activeIndicator=" + activeIndicator +
                ", communityManagerId=" + communityManagerId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", zip4=" + zip4 +
                '}';
    }
}
