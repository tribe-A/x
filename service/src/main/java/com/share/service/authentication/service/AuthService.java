package com.share.service.authentication.service;

import com.share.common.pojo.dto.UserInfoDto;
import com.share.dao.UserInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserInfoMapper userInfoMapper;

    public AuthService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfoDto authentication(String userId, String password) {
        return null;
    }

}
