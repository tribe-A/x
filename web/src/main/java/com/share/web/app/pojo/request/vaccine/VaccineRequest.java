package com.share.web.app.pojo.request.vaccine;

import com.share.web.app.pojo.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class VaccineRequest extends BaseRequest {

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
