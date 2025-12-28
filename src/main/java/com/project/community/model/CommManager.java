package com.project.community.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "community_manager_association")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="manager_id")
    private int managerId;

    @Column(name="community_id")
    private int communityId;
}
