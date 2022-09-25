package com.share.common.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DeWormDto {

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

}
