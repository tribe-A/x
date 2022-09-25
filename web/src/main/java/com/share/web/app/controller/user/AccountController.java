package com.share.web.app.controller.user;

import com.share.common.pojo.dto.UserInfoDto;
import com.share.service.authentication.service.AccountService;
import com.share.service.authentication.service.AuthService;
import com.share.web.app.pojo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private AuthService authService;
    private AccountService accountService;

    public AccountController (AuthService authService,AccountService accountService) {
        this.authService = authService;
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public UserVo signUp(@RequestBody UserVo user) {
        log.info("注册用户");
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        userInfoDto.setUserPassword(user.getPassword());
        UserInfoDto registerUserInfo = accountService.registerAccount(userInfoDto);
        UserVo result = new UserVo();
        BeanUtils.copyProperties(registerUserInfo,result);
        return result;
    }
    @PostMapping("/login")
    public UserVo logIn(@RequestBody UserVo user) {
        log.info("接收请求");
        if (StringUtils.isNotBlank(user.getAgcToken())) {
            return null;
        }else {
            UserInfoDto userInfoDto = authService.authentication(user.getUserId(), user.getPassword());
            if (Objects.isNull(userInfoDto)) {
                return null;
            }
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userInfoDto,userVo);
            return userVo;
        }
    }
    @PostMapping("/logout")
    public String logOut(@RequestBody UserVo user) {
        log.info("接收请求");
        return "1";
    }
}
