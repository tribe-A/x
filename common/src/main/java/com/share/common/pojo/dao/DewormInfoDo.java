package com.share.common.pojo.dao;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * deworm_info
 * @author
 */
@Data
public class DewormInfoDo implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 驱虫ID
     */
    private String dewormId;

    private String petId;

    /**
     * 驱虫日期
     */
    private Date dewormDate;

    /**
     * 驱虫人员
     */
    private String dewormDoctor;

    /**
     * 地点
     */
    private String dewormSite;

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
    private Integer dewormStatus;

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
