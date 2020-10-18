package com.test.sdk.script.util;

import lombok.Data;

/**
 * @author 徒有琴
 */
@Data
public class AjaxResult {
    private boolean status;
    private Object result;

    public static AjaxResult ok(Object result) {
        AjaxResult ajax = new AjaxResult();
        ajax.status = true;
        ajax.result = result;
        return ajax;
    }

    public static AjaxResult error(Object result) {
        AjaxResult ajax = new AjaxResult();
        ajax.status = false;
        ajax.result = result;
        return ajax;
    }
}
