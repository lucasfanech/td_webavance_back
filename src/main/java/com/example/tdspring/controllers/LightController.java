package com.example.tdspring.controllers;

import com.example.tdspring.models.Light;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lights")
public class LightController {
    @GetMapping()
    public ResponseEntity<Light> getLight() {
        Light light = new Light();
        light.setId(1);
        light.setToggled(true);
        light.setTitle("Main Light");
        light.setColor("White");
        return new ResponseEntity<>(light, HttpStatus.OK);
    }

}
