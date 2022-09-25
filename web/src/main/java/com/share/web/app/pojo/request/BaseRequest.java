package com.share.web.app.pojo.request;

import lombok.Data;

@Data
public class BaseRequest {
    private String userId;
    private int pageNum;
    private int pageSize;
}
