package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Zip Code info from https://api.zippopotam.us/")
@Slf4j
@RestController
@RequestMapping("/api/zipcodes")
public class ZipCodeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService ZipCodeQueryService;

    @Operation(summary = "Get a Zipcode's geographic information", description = "Zip code data adapted from geonames.org using Zippopotam.us api")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCodes(
            @Parameter(name = "zipcode", example = "93106") @RequestParam String zipcode)
            throws JsonProcessingException {
        log.info("getZipCodes: zipcode={}", zipcode);
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}
