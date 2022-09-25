package com.share.common.pojo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileCallBack {
    private String key;
    private String bucket;
    private String mimeType;
}
