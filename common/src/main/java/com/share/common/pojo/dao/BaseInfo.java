package com.share.common.pojo.dao;


import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class BaseInfo {
    private Long id;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
}
