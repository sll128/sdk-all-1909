package com.test.sdk.core.exception;

import com.test.sdk.common.pojo.LocalePrompt;
import com.test.sdk.common.pojo.PromotionChannel;
import com.test.sdk.common.util.ErrorCode;
import com.test.sdk.common.util.JsonUtil;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.cache.LocalePromptCache;
import com.test.sdk.core.cache.PromotionChannelCache;
import com.test.sdk.core.util.ErrorConstants;
import com.test.sdk.core.util.ParameterUtil;
import org.apache.ibatis.logging.Log;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 徒有琴
 */
@Component
public class SdkExceptionHandler implements HandlerExceptionResolver {
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @Nullable Object o, Exception e) {
        handler(httpServletRequest, httpServletResponse, e);
        return null;
    }

    public static void handler(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) {
        ResponseTO result = new ResponseTO();
        ErrorCode code = null;
        e.printStackTrace();
        if (e instanceof SdkException) {//系统自定义的异常，是我们手动抛出的
            //根据渠道找到渠道对应的语言，然后把异常信息转化为国际化
            Integer prtchid = ParameterUtil.getInteger(httpServletRequest, "prtchid");
            Integer languageType = PromotionChannelCache.getLanguageType(prtchid);
            LocalePrompt prompt = LocalePromptCache.getPromopt(e.getMessage(), languageType);
            if (languageType == null || prompt == null) {
                code = ErrorConstants.SYSTEM_UNKNOWN;
            } else {
                code = new ErrorCode(prompt.getCode(), prompt.getValue());
            }
        } else {//意料之外的异常
            code = ErrorConstants.SYSTEM_UNKNOWN;
        }
        result.setErrorCode(code);
        String json = JsonUtil.getJSON(result);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        try {
            httpServletResponse.getWriter().write(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
