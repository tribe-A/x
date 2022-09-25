package com.share.web.app.auth;

import com.huawei.agconnect.server.auth.entity.AuthAccessToken;
import com.share.web.app.pojo.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class UserIdAspect {

    @Pointcut("@annotation(com.share.web.app.auth.ReplaceUserId)")
    public void userPointCut() {}

    @Before("userPointCut()")
    public void replaceUserId(JoinPoint joinPoint) {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        AuthAccessToken user = (AuthAccessToken)request.getAttribute("user");
        long sub = user.getSub();
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BaseRequest) {
                BaseRequest req = (BaseRequest)arg;
                req.setUserId(String.valueOf(sub));
            }
        }
    }
}
