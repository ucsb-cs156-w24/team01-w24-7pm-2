package edu.ucsb.cs156.spring.backenddemo.services;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZipCodeQueryService {

    ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public ZipCodeQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "http://api.zippopotam.us/us/{zipcode}";

    public String getJSON(String postcode, String country, String countryabbreviation, String places)
            throws HttpClientErrorException {
        log.info("post code={}, country={}, country abbreviation={}, places={}", postcode, country, countryabbreviation,
                places);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> uriVariables = Map.of("post code", postcode, "country", country, "country abbreviation",
                countryabbreviation,
                "places", places);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }

}