package com.test.sdk.common.util;

import lombok.Data;

/**
 * sdk接口给前端的统一响应
 *
 * @author 徒有琴
 */
@Data
public class ResponseTO {
    private boolean success;
    private Object businessResult;
    private ErrorCode errorCode;
    private String ticket;

    public static ResponseTO ok(Object businessResult) {
        ResponseTO response = new ResponseTO();
        response.setSuccess(true);
        response.setBusinessResult(businessResult);
        return response;
    }

    public static ResponseTO error(Object businessResult) {
        ResponseTO response = new ResponseTO();
        response.setSuccess(false);
        response.setBusinessResult(businessResult);
        return response;
    }
}
