package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Tag(name="Locations from https://nominatim.openstreetmap.org/")
@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService locationQueryService;

    @Operation(summary = "Get a location")
    @GetMapping("/get")
    public ResponseEntity<String> getLocation() throws JsonProcessingException {
        String result = locationQueryService.getJSON("Isla Vista");
        return ResponseEntity.ok().body(result);
    }

}