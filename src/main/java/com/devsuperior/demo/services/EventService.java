package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repository.CityRepository;
import com.devsuperior.demo.repository.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO eventDTO) {

        try {
            Event event = repository.getReferenceById(id);
            event.setName(eventDTO.getName());
            event.setDate(eventDTO.getDate());
            event.setUrl(eventDTO.getUrl());

            City city = cityRepository
                    .findById(eventDTO.getCityId())
                    .orElseThrow(() -> new ResourceNotFound("Id não encontrado"));

            event.setCity(city);

            event = repository.save(event);

            return new EventDTO(event);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFound("Id não encontrado");
        }

    }
}
