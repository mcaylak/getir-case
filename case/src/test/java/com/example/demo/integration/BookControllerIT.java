package com.example.demo.integration;

import com.getir.demo.CaseApplication;
import com.getir.demo.common.request.BookCreateRequest;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.BookDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BookControllerIT
 * Author: mcaylak
 * Since : 9.10.2022
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    void testCreateBook() throws URISyntaxException, JSONException {

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAuthorizationToken());
        BookCreateRequest bookCreateRequest = prepareBookCreateRequest();

        RequestEntity requestEntity = new RequestEntity(bookCreateRequest, headers,
                HttpMethod.POST, new URI(createURLWithPort("/api/book/create")));

        ResponseEntity<ResponseBean<BookDto>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<ResponseBean<BookDto>>() {
                });

        assertEquals(bookCreateRequest.getName(), Objects.requireNonNull(responseEntity.getBody()).getData().get(0).getName());
    }

    private BookCreateRequest prepareBookCreateRequest() {
        return BookCreateRequest.builder()
                .stock(10L)
                .price(100L)
                .name("Test Book")
                .description("Test Book Description")
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
