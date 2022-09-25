package com.share.common.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String userId;
    private String nickName;
    private String userPassword;
    private String salt;
    private Integer userStatus;
}
