package com.example.tdspring.controllers;

import com.example.tdspring.exceptions.DBException;
import com.example.tdspring.exceptions.NotFoundException;
import com.example.tdspring.models.Light;
import com.example.tdspring.services.LightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lights")
@RequiredArgsConstructor
@Slf4j
public class LightController {

    private final LightService lightService;

    @GetMapping
    public ResponseEntity<List<Light>> getLights() {
        return new ResponseEntity<>(this.lightService.getAllLights(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Light> postLight(@RequestBody Light lightSent) {
        try {
            log.info("Creating light ...");
            return lightSent.getId() == null ?
                    new ResponseEntity<>(this.lightService.updateLight(lightSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.lightService.updateLight(lightSent), HttpStatus.ACCEPTED);
        } catch (DBException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Light> deleteLight(@PathVariable Long id) {
        try {
            log.info("Deleting light ...");
            return new ResponseEntity<>(this.lightService.deleteLight(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
