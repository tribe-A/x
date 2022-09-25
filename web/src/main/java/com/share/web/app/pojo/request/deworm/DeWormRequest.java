package com.share.web.app.pojo.request.deworm;

import com.share.web.app.pojo.request.BaseRequest;
import lombok.Data;

import java.util.Date;


@Data
public class DeWormRequest extends BaseRequest {

    private String petId;

    /**
     * 驱虫ID
     */
    private String dewormId;

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
}
