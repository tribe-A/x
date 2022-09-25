package com.share.common.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageNumber {
    private int pageNum = 1;
    private int pageSize = 10;
}
