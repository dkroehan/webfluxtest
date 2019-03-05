package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class Controller {


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "test/{id}")
    public Mono<String> add(@PathVariable("id") UUID id) {
        return Mono.just(id.toString());
    }



}
