package com.project.community.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.community.repository.CommunityRepository;
import com.project.community.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommunityController.class)
class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommunityService communityService;

    @MockitoBean
    private CommunityRepository communityRepository;

    @InjectMocks
    private CommunityController communityController;

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void save() throws Exception {
//        CommunityDTO communityDTO = new CommunityDTO("Ashford", CommunityType.SINGLE_FAMILY,
//                true,(short)2,"Plainsboro","NJ","08536","100");
//        communityDTO.setCommunityId((short) 100);
//        when(communityService.save(any())).thenReturn(communityDTO);
//        mockMvc.perform(post("/community")
//                .content(objectMapper.writeValueAsString(communityDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().string("100"));
//    }

    @Test
    void findByCommunityType() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteByCommunityId() {
    }
}