package com.project.community.service;

import com.project.community.common.library.dto.UserDTO;
import com.project.community.common.library.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class APIService {

    @Autowired
    private WebClient webClient;

    @Value("${usernamepath}")
    private String usernamePath;

    public Users callPostApi(UserDTO request) {
        Users response =  webClient.post()
                .uri("/auth/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Users.class)
                .block();
        return response;
    }

    public Optional<Users> callFindByUsername(String username){
        Optional<Users> response = Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder.path(usernamePath).queryParam("name", username).build())
                .retrieve()
                .bodyToMono(Users.class)
                .block());
        return response;
    }

}
