package com.api.parkingcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/listar")
    public String listar() {
        return "Olá mundo";
    }
}
