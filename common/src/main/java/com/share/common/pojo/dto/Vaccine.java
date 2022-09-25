package com.share.common.pojo.dto;

import lombok.Data;

import java.util.Date;


@Data
public class Vaccine {

    private static final long serialVersionUID = 134342345452233L;

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

}
