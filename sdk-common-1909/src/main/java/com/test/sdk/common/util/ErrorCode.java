package com.test.sdk.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 徒有琴
 */
@Data
public class ErrorCode implements Serializable{
    private String code;
    private String message;

    public ErrorCode() {
    }

    public ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
