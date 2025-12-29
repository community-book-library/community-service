package com.project.community.service;

import com.project.community.common.library.dto.UserDTO;
import com.project.community.common.library.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class APIService {

    @Autowired
    private WebClient webClient;

    public Users callPostApi(UserDTO request) {
        //Arun: try non-blocking webclient if possible
        Users response =  webClient.post()
                .uri("/auth/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Users.class)
                .block();
        return response;
    }

}
