package com.getir.demo.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * LoginResponse
 * Author: mcaylak
 * Since : 6.10.2022
 */

@Getter
@Setter
@Builder
public class LoginResponse {

    private String username;
    private String token;
    private String tokenType;

}
