package com.project.community.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", unique = true, nullable = false)
    private short communityId;

    @Column(name = "community_name", nullable = false)
    private String communityName;

    @Enumerated(EnumType.STRING)
    @Column(name = "community_type", nullable = false)
    private CommunityType communityType;

    @Column(name = "active_indicator", nullable = false)
    private char activeIndicator;

    @Column(name = "community_manager_id")
    private short communityManagerId;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "zip",nullable = false)
    private String zip;

    @Column(name = "zip4",nullable = false)
    private String zip4;

    @Column(name = "created_timestamp", nullable = false)
    private Timestamp createdTimestamp;

    @Column(name = "updated_timestamp")
    private Timestamp updatedTimestamp;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    public Community() {

    }

    public Community(String communityName, short communityManagerId, String city, String state, String zip, String zip4) {
        this.communityName = communityName;
        this.communityManagerId = communityManagerId;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.zip4 = zip4;
    }

    public Community(String communityName, CommunityType communityType, char activeIndicator, short communityManagerId,
                     String city, String state, String zip, String zip4, Timestamp createdTimestamp,
                     Timestamp updatedTimestamp, String createdBy, String updatedBy) {
        this.communityName = communityName;
        this.communityType = communityType;
        this.activeIndicator = activeIndicator;
        this.communityManagerId = communityManagerId;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.zip4 = zip4;
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Community(short communityId, String communityName, char activeIndicator, short communityManagerId, String city, String state, String zip, String zip4, Timestamp createdTimestamp, Timestamp updatedTimestamp, String createdBy, String updatedBy) {
        this.communityId = communityId;
        this.communityName = communityName;
        this.activeIndicator = activeIndicator;
        this.communityManagerId = communityManagerId;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.zip4 = zip4;
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public short getCommunityId() {
        return communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public CommunityType getCommunityType() {
        return communityType;
    }



    public short getCommunityManagerId() {
        return communityManagerId;
    }

    public void setCommunityManagerId(short communityManagerId) {
        this.communityManagerId = communityManagerId;
    }

    @Override
    public String toString() {
        return "Community{" +
                "communityId=" + communityId +
                ", communityName='" + communityName + '\'' +
                ", communityType=" + communityType +
                ", activeIndicator=" + activeIndicator +
                ", communityManagerId=" + communityManagerId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", zip4=" + zip4 +
                ", createdTimestamp=" + createdTimestamp +
                ", updatedTimestamp=" + updatedTimestamp +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setCommunityId(short communityId) {
        this.communityId = communityId;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setCommunityType(CommunityType communityType) {
        this.communityType = communityType;
    }

    public char getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(char activeIndicator) {
        this.activeIndicator = activeIndicator;
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

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
