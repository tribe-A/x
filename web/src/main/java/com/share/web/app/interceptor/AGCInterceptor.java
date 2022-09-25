package com.share.web.app.interceptor;

import com.huawei.agconnect.server.auth.entity.AuthAccessToken;
import com.huawei.agconnect.server.auth.exception.AGCAuthException;
import com.huawei.agconnect.server.auth.exception.AuthErrorCode;
import com.huawei.agconnect.server.auth.service.AGCAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
public class AGCInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("agc");
        if (StringUtils.isNotBlank(token)) {
            try {
                AuthAccessToken authAccessToken = AGCAuth.getInstance().verifyAccessToken(token, true);
                if (Objects.nonNull(authAccessToken)) {
                    request.setAttribute("user",authAccessToken);
                    return true;
                }
            } catch (AGCAuthException e) {
                log.error("认证失败", e);
                if (e.getErrorCode() == AuthErrorCode.VERIFY_ACCESS_TOKEN_ACCESS_TOKEN_IS_NULL.getErrorCode()) {
                    // 用户访问凭据为空
                } else if (e.getErrorCode() == AuthErrorCode.JWT_VERIFY_FAILED.getErrorCode()) {
                    // 用户访问凭据验证失败
                } else if (e.getErrorCode() == AuthErrorCode.JWT_EXPIRE.getErrorCode()) {
                    // 用户访问凭据已过期
                } else if (e.getErrorCode() == AuthErrorCode.JWT_REVOKED.getErrorCode()) {
                    // 用户访问凭据已撤销
                }
            } catch (Exception e) {
                log.info("发生异常");
            }
        }
        AuthAccessToken mock = new AuthAccessToken();
        mock.setName("小明");
        mock.setSub(957671096759354112L);
        request.setAttribute("user",mock);
        return true;
    }
}

