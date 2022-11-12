package com.example.webclient.service;

import com.example.webclient.entity.Users;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://127.0.0.1:8000").build();
    }

    public Flux<Users> index() {
        Flux<Users> users = webClient.get().uri("/user").retrieve().bodyToFlux(Users.class);
        return users;
    }

    public Mono<Users> show() {
        Mono<Users> user = webClient.get().uri("/show").retrieve().bodyToMono(Users.class);
        return  user;
    }

    public Mono<ClientResponse> saveUser(Users user) {
        return webClient.post().uri("/store")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user),Users.class)
                .exchange();
    }
}
