package com.share.common.pojo.dao;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class VaccinesInfoPo extends BaseInfo{
    private String vaccineId;
    private Date vaccineDate;
    private String vaccineDoctor;
    private String vaccineSite;
    private Date nextDate;
    private String remark;
    private Integer vaccineStatus;
}
