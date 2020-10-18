package com.test.sdk.core.filter;

import com.test.sdk.common.util.JsonUtil;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.cache.CPCache;
import com.test.sdk.core.exception.SdkException;
import com.test.sdk.core.exception.SdkExceptionHandler;
import com.test.sdk.core.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author 徒有琴
 */
@WebFilter("/*")
public class SystemFilter implements Filter {
    public final String NEED_LOGIN_PATH = "^/user/service/.*$";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Integer cpid = ParameterUtil.getInteger(request, "cpid");
        Integer prtchid = ParameterUtil.getInteger(request, "prtchid");
        Integer seqnum = ParameterUtil.getInteger(request, "seqnum");
        if (cpid == null || prtchid == null || seqnum == null) {
            SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.PARAM_ERROR));
            return;
        }
        String secretKey = CPCache.getSecretKey(cpid);
        if (secretKey == null) {//cp不存在或已经失效
            SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.CP_NOT_EXIST));
            return;
        }
        Map<String, String[]> params = request.getParameterMap();
        String signStr = SignUtil.getSignString(params);
        String mySign = SignUtil.getMD5(signStr + secretKey);
        System.out.println(mySign);
        String sign = request.getParameter("sign");
        if (mySign.equals(sign)) {
            try {
                String path = request.getServletPath();
                System.out.println(path);
                if (path.matches(NEED_LOGIN_PATH)) {//需要登录
                    String ticket = ParameterUtil.getString(request, "ticket");
                    if (ticket == null) {
                        SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.SESSION_TIME_OUT));
                        return;
                    }
                    Date time = TicketUtil.getTime(ticket);
                    if (time == null) {
                        SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.PARAM_ERROR));
                        return;
                    }
                    Date now = new Date();
                    if ((now.getTime() - time.getTime()) / 1000 > SdkConstants.USER_SESSION_TIMEOUT) {
                        SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.SESSION_TIME_OUT));
                        return;
                    }
                }
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                SdkExceptionHandler.handler(request, response, e);
            }
        } else {
            SdkExceptionHandler.handler(request, response, new SdkException(ErrorConstants.SIGN_ERROR));
        }
    }
}
