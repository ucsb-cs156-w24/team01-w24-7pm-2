package edu.ucsb.cs156.spring.backenddemo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;

@WebMvcTest(value = ZipCodeController.class)
public class ZipCodeControllerTests {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ZipCodeQueryService mockZipCodeQueryService;

    @Test
    public void test_getZipCodes() throws Exception {

        String fakeJsonResult = "{ \"fake\" : \"result\" }";
        String zipcode = "93106";
        when(mockZipCodeQueryService.getJSON(eq(zipcode))).thenReturn(fakeJsonResult);

        String url = String.format("/api/zipcodes/get?zipcode=%s", zipcode);

        MvcResult response = mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        String responseString = response.getResponse().getContentAsString();

        assertEquals(fakeJsonResult, responseString);
    }

}
