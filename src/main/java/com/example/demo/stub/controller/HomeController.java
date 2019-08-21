package com.example.demo.stub.controller;

import java.util.Arrays;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.stub.domain.CityInfo;
import com.example.demo.stub.domain.ShippingInfo;
import com.example.demo.stub.repository.SpringRepo;

@RestController
public class HomeController {

    private final SpringRepo repo;

    @Autowired
    public HomeController(SpringRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> cities() {
        return new ResponseEntity<>(repo.getCities(), HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<List<String>> size() {
        return new ResponseEntity<>(repo.getSizes(), HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<String>> type() {
        return new ResponseEntity<>(repo.getTypes(), HttpStatus.OK);
    }

    @GetMapping("/velocity")
    public ResponseEntity<List<String>> velocity() {
        return new ResponseEntity<>(repo.getVelocity(), HttpStatus.OK);
    }

    @GetMapping("/transport")
    public ResponseEntity<List<String>> transport() {
        return new ResponseEntity<>(repo.getTransport(), HttpStatus.OK);
    }

    @PostMapping("/cityPath")
    public ResponseEntity<String> path(@RequestBody CityInfo cityInfo){
        String path = cityInfo.getOrigin() + "=>" + cityInfo.getDestination();
        return new ResponseEntity<>(path, HttpStatus.OK);
    }

    @PostMapping("/checkPrice")
    public ResponseEntity<Double> checkPrice(@RequestBody ShippingInfo shippingInfo){
        return new ResponseEntity<>(156.69, HttpStatus.OK);
    }

    @PostMapping("/sendShipping")
    public ResponseEntity<ShippingInfo> shipping(@RequestBody ShippingInfo shippingInfo){
        shippingInfo.setFolio(123456);
        return new ResponseEntity<>(shippingInfo, HttpStatus.OK);
    }

    @GetMapping("/shippingInformation")
    public ResponseEntity<List<ShippingInfo>> shipping(){
        ShippingInfo shippingInfo1 = new ShippingInfo(123.45, 123, "Chihuahua => Chiapas");
        ShippingInfo shippingInfo2 = new ShippingInfo(666.66, 135, "Baja California Sur => Chihuahua");
        ShippingInfo shippingInfo3 = new ShippingInfo(534.99, 278, "Hemosillo => Ciudad de Mexico");
        return new ResponseEntity<>(Arrays.asList(shippingInfo1, shippingInfo2, shippingInfo3), HttpStatus.OK);
    }
}

