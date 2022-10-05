package com.getir.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * Author: mcaylak
 * Since : 5.10.2022
 */

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
