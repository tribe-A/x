package com.share.web.app.pojo.response;

import lombok.Data;

@Data
public class BaseResponse {
    private String code;
    private Object data;
    private String msg;

    public static BaseResponse response(boolean success,Object data,String successMsg,String failMsg) {
        if (success) {
            return new BaseResponse("200",data,successMsg);
        }else {
            return new BaseResponse("500",data,failMsg);
        }
    }

    private BaseResponse(String code,Object data,String msg) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
