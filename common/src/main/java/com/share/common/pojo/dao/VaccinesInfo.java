package com.share.common.pojo.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * vaccines_info
 * @author
 */
@Data
public class VaccinesInfo implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 疫苗ID
     */
    private String vaccineId;

    private String petId;

    /**
     * 疫苗日期
     */
    private Date vaccineDate;

    /**
     * 疫苗人员
     */
    private String vaccineDoctor;

    /**
     * 地点
     */
    private String vaccineSite;

    /**
     * 下次日期
     */
    private Date nextDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Integer vaccineStatus;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
