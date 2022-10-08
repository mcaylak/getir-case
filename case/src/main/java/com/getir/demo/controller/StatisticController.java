package com.getir.demo.controller;

import com.getir.demo.common.response.CustomerMonthlyStatisticsResponse;
import com.getir.demo.common.response.ResponseBean;
import com.getir.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * StatisticController
 * Author: mcaylak
 * Since : 8.10.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/statistic")
public class StatisticController {

    private OrderService orderService;

    @GetMapping
    public ResponseBean<CustomerMonthlyStatisticsResponse> placeOrder(@RequestParam(required = true) Long customerId) {
        return new ResponseBean<>(orderService.customerMonthlyStatistics(customerId));
    }

}
