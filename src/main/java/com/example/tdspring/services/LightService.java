package com.example.tdspring.services;

import com.example.tdspring.exceptions.DBException;
import com.example.tdspring.exceptions.NotFoundException;
import com.example.tdspring.models.Light;
import com.example.tdspring.repositories.LightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;

    public List<Light> getAllLights() {
        return this.lightRepository.findAll();
    }

    public Light updateLight(Light light) throws DBException, NotFoundException {
        Light existing;

        if (light.getId() != null) {
            // On veut modifier une lampe
            existing = this.lightRepository.findById(light.getId()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find light with id : " + light.getId());
        } else {
            existing = new Light();
        }

        existing.setToggled(light.getToggled());
        existing.setColor(light.getColor());
        existing.setTitle(light.getTitle());

        try {
            Light lightCreated = this.lightRepository.save(existing);
            return lightCreated;
        } catch (Exception e) {
            throw new DBException("Could not create light");
        }
    }

    public Light deleteLight(Long id) throws NotFoundException, DBException {
        Light existing = this.lightRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Could not find light with id : " + id);

        try {
            this.lightRepository.delete(existing);
            return existing;
        } catch (Exception e) {
            throw new DBException("Could not delete light");
        }
    }
}
