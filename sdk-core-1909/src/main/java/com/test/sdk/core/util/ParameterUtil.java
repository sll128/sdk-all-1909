package com.test.sdk.core.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 徒有琴
 */
public class ParameterUtil {
    public static Integer getInteger(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (StringUtils.isEmpty(param)) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null || param.trim().length() == 0) {
            return null;
        }
        return param.trim();
    }
}
