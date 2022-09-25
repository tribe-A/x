package com.share.service.authentication.service;

import com.share.common.pojo.dto.UserInfoDto;
import com.share.dao.UserInfoMapper;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private UserInfoMapper userInfoMapper;

    public AccountService(UserInfoMapper mapper) {
        userInfoMapper = mapper;
    }


    public UserInfoDto registerAccount(UserInfoDto user) {

        return null;
    }
}
