package com.java.school.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.school.domain.City;
import com.java.school.domain.PackageSize;
import com.java.school.domain.PackageType;
import com.java.school.domain.TransportType;
import com.java.school.domain.TransportVelocity;
import com.java.school.repository.ApplicationRepository;

@RestController
public class HomeController {

    private final ApplicationRepository repo;

    @Autowired
    public HomeController(ApplicationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> cities() {
        return new ResponseEntity<>(repo.getCities().stream().map(City::getName).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<List<String>> size(@RequestParam String packateTypeName) {
        return new ResponseEntity<>(repo.getPackageSizesByPackageType(packateTypeName).stream().map(PackageSize::getDescription).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<String>> type() {
        return new ResponseEntity<>(repo.getPackageTypes().stream().map(PackageType::getDescription).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/velocity")
    public ResponseEntity<List<String>> velocity() {
        return new ResponseEntity<>(repo.getTransportVelocity().stream().map(TransportVelocity::getDescription).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/transport")
    public ResponseEntity<List<String>> transport() {
        return new ResponseEntity<>(repo.getTransportTypes().stream().map(TransportType::getDescription).collect(Collectors.toList()), HttpStatus.OK);
    }

//    @PostMapping("/cityPath")
//    public ResponseEntity<String> path(@RequestBody CityInfo cityInfo){
//        String path = cityInfo.getOrigin() + "=>" + cityInfo.getDestination();
//        return new ResponseEntity<>(path, HttpStatus.OK);
//    }

//    @PostMapping("/checkPrice")
//    public ResponseEntity<Double> checkPrice(@RequestBody ShippingInfo shippingInfo){
//        return new ResponseEntity<>(156.69, HttpStatus.OK);
//    }

//    @PostMapping("/sendShipping")
//    public ResponseEntity<ShippingInfo> shipping(@RequestBody ShippingInfo shippingInfo){
//        shippingInfo.setFolio(123456);
//        return new ResponseEntity<>(shippingInfo, HttpStatus.OK);
//    }

//    @GetMapping("/shippingInformation")
//    public ResponseEntity<List<ShippingInfo>> shipping(){
//        ShippingInfo shippingInfo1 = new ShippingInfo(123.45, 123, "Chihuahua => Chiapas");
//        ShippingInfo shippingInfo2 = new ShippingInfo(666.66, 135, "Baja California Sur => Chihuahua");
//        ShippingInfo shippingInfo3 = new ShippingInfo(534.99, 278, "Hemosillo => Ciudad de Mexico");
//        return new ResponseEntity<>(Arrays.asList(shippingInfo1, shippingInfo2, shippingInfo3), HttpStatus.OK);
//    }
}

