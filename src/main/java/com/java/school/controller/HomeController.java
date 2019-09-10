package com.java.school.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.java.school.config.AMQConfiguration;
import com.java.school.domain.City;
import com.java.school.domain.PackageSize;
import com.java.school.domain.PackageType;
import com.java.school.domain.TransportType;
import com.java.school.domain.TransportVelocity;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

    private final ApplicationRepository repo;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    @Autowired
    public HomeController(ApplicationRepository repo, RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.repo = repo;
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> cities() {
        return new ResponseEntity<>(repo.getCities().stream().map(City::getName).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/sizeByType")
    public ResponseEntity<List<String>> size(@RequestParam String packageTypeName) {
        return new ResponseEntity<>(repo.getPackageSizesByPackageType(packageTypeName).stream().map(PackageSize::getDescription).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<List<String>> size() {
        return new ResponseEntity<>(repo.getPackageSizes().stream().map(PackageSize::getDescription).collect(Collectors.toList()), HttpStatus.OK);
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

    @GetMapping("/cities/json")
    public ResponseEntity<List<City>> citiesJson() {
        return new ResponseEntity<>(repo.getCities(), HttpStatus.OK);
    }

    @GetMapping("/size/json")
    public ResponseEntity<List<PackageSize>> sizeJson() {
        return new ResponseEntity<>(repo.getPackageSizes(), HttpStatus.OK);
    }

    @GetMapping("/type/json")
    public ResponseEntity<List<PackageType>> typeJson() {
        return new ResponseEntity<>(repo.getPackageTypes(), HttpStatus.OK);
    }

    @GetMapping("/velocity/json")
    public ResponseEntity<List<TransportVelocity>> velocityJson() {
        return new ResponseEntity<>(repo.getTransportVelocity(), HttpStatus.OK);
    }

    @GetMapping("/transport/json")
    public ResponseEntity<List<TransportType>> transportJson() {
        return new ResponseEntity<>(repo.getTransportTypes(), HttpStatus.OK);
    }

    @GetMapping("/runner/{message}")
    public ResponseEntity<JsonNode> run(@PathVariable String message) throws IOException {
        logger.info("Requesting table: " + message);
        ObjectNode messageJson = JsonNodeFactory.instance.objectNode();
        messageJson.put("type", message);
        String packageTypes = (String) rabbitTemplate.convertSendAndReceive(AMQConfiguration.TOPIC_EXCHANGE_NAME, "#", messageJson.toString());
        logger.info("Receiving table: " + packageTypes);
        return new ResponseEntity<>(mapper.readValue(packageTypes, JsonNode.class), HttpStatus.OK);
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

