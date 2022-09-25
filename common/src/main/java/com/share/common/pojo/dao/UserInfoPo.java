package com.share.common.pojo.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoPo extends BaseInfo{
    private String userId;
    private String nickName;
    private String userPassword;
    private String salt;
    private Integer userStatus;
}
