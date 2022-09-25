package com.share.dao;

import com.share.common.pojo.dao.UserInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    UserInfoPo selectUserInfoById(@Param("userId") String userId);
    Integer addUserInfo(UserInfoPo user);
    Integer updateUserInfo(UserInfoPo user);
}
