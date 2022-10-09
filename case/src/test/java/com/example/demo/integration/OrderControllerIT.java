package com.example.demo.integration;

import com.getir.demo.CaseApplication;
import com.getir.demo.common.response.LoginResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.dto.OrderDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
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
 * OrderControlerIT
 * Author: mcaylak
 * Since : 8.10.2022
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    void testFindAllByCustomerId() throws JSONException, URISyntaxException {

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAuthorizationToken());

        RequestEntity requestEntity = new RequestEntity(null, headers,
                HttpMethod.GET, new URI(createURLWithPort("/api/order/findAllByCustomerId?customerId=1")));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(requestEntity, String.class);

        String expected = getFindAllByCustomerExampleIdReq();

        JSONAssert.assertEquals(expected, Objects.requireNonNull(responseEntity.getBody()), new CustomComparator(
                JSONCompareMode.STRICT,
                Customization.customization("timeStamp",
                        new ValueMatcher() {
                            @Override
                            public boolean equal(Object o1, Object o2) {
                                return true;
                            }
                        })));

    }


    @Test
    void testGetOrderDetailsReturnErrorMessage() throws JSONException, URISyntaxException {

        long orderId = 1;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAuthorizationToken());

        RequestEntity requestEntity = new RequestEntity(null, headers,
                HttpMethod.GET, new URI(createURLWithPort("/api/order/orderDetails?orderId=0")));

        ResponseEntity<ResponseBean<OrderDto>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<ResponseBean<OrderDto>>() {
                });


        assertEquals("Order Id {0} not found in database!", Objects.requireNonNull(responseEntity.getBody()).getMessage());

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

    private String getFindAllByCustomerExampleIdReq() {
        return "{\"code\":200,\"data\":[{\"content\":[],\"pageable\":{\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true}," +
                "\"offset\":0,\"pageNumber\":0,\"pageSize\":3,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":0,\"totalElements\":0,\"size\"" +
                ":3,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":0,\"empty\":true}]," +
                "\"message\":null,\"timeStamp\":1665308668598}";
    }


}
