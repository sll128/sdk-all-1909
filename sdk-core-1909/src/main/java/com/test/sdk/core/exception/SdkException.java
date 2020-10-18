package com.test.sdk.core.exception;

import com.test.sdk.common.util.ErrorCode;

/**
 * @author 徒有琴
 */
public class SdkException extends Exception {
    private ErrorCode errorCode;

    public SdkException() {
    }

    public SdkException(String message) {
        super(message);
    }

    public SdkException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
