package com.example.demo.integration;

import com.getir.demo.CaseApplication;
import com.getir.demo.common.request.CreateCustomerRequest;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.CustomerDto;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CustomerControllerIT
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    void testCreateUser() throws URISyntaxException, JSONException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAuthorizationToken());

        CreateCustomerRequest createCustomerRequest = prepareCreateCustomerRequest();

        RequestEntity requestEntity = new RequestEntity(createCustomerRequest, headers,
                HttpMethod.POST, new URI(createURLWithPort("/api/customer/create")));

        ResponseEntity<ResponseBean<CustomerDto>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<ResponseBean<CustomerDto>>() {
                });

        assertEquals(createCustomerRequest.getEmail(), Objects.requireNonNull(responseEntity.getBody()).getData().get(0).getEmail());
    }

    private CreateCustomerRequest prepareCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .name("Test customer")
                .email(UUID.randomUUID() + "@test.com")
                .username(UUID.randomUUID().toString().substring(0, 10))
                .password("testpassword00")
                .build();
    }

    private String getAuthorizationToken() throws JSONException, URISyntaxException {

        JSONObject parameters = new JSONObject();

        parameters.put("username", "Caylaks");
        parameters.put("password", "spyware00");

        RequestEntity requestEntity = new RequestEntity(parameters.toString(), headers,
                HttpMethod.POST, new URI(createURLWithPort("/api/login")));

        ResponseEntity<ResponseBean<LoginResponse>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<ResponseBean<LoginResponse>>() {
                });

        return Objects.requireNonNull(responseEntity.getBody()).getData().get(0).getToken();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
