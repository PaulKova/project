package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ShopDto;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShopControllerTest1 {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void getAllShops() {
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<ShopDto>> response = testRestTemplate
                .exchange("/api/shops",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        List<ShopDto> shopsDto = response.getBody();
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}