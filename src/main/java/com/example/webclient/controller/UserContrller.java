package com.example.webclient.controller;

import com.example.webclient.entity.Users;
import com.example.webclient.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserContrller {
    @Autowired
    private WebClientService webClientService;

    @GetMapping("/users")
    public Flux<Users> find() {
        return webClientService.index();
    }

    @GetMapping("/show")
    public Mono<Users> show() {
        return webClientService.show();
    }

}
