package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cities")
@RestController
public class HomeController {

    @RequestMapping
    public ResponseEntity<List<City>> home() throws Exception {
//        throw new RuntimeException("My F mesage");
        return new ResponseEntity<>(Arrays.asList(new City("Chihuahua", 25), new City("Chiapas", 50)), HttpStatus.OK);
    }
}

