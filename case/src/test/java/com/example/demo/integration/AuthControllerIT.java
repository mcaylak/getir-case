package com.example.demo.integration;

import com.getir.demo.CaseApplication;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * AuthControllerIT
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    void testLogin() throws JSONException, URISyntaxException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject parameters = new JSONObject();

        parameters.put("username", "Caylaks");
        parameters.put("password", "spyware00");

        RequestEntity requestEntity = new RequestEntity(parameters.toString(), headers,
                HttpMethod.POST, new URI(createURLWithPort("/api/login")));

        ResponseEntity<ResponseBean<LoginResponse>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
                });

        assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getData().get(0).getToken());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
