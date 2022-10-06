package com.getir.demo.common.response;

import com.getir.demo.common.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ResponseBean
 * Author: mcaylak
 * Since : 6.10.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> {
    private Integer code;
    private List<T> data;
    private String message;
    private long timeStamp = System.currentTimeMillis();

    public ResponseBean(List<T> data) {
        this.data = data;
        this.code = ErrorCode.OK;
    }

    public ResponseBean(T data) {
        this.data = new ArrayList<>();
        this.data.add(data);
        this.code = ErrorCode.OK;
    }

    public ResponseBean(Integer code, T data) {
        this.data = new ArrayList<>();
        this.data.add(data);
        this.code = code;
    }

    public ResponseBean(Integer code, T data, String message) {
        this.data = new ArrayList<>();
        this.data.add(data);
        this.message = message;
        this.code = code;
    }

    public ResponseBean(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public ResponseBean(Integer code, List<T> data) {
        this.data = data;
        this.code = code;
    }
}