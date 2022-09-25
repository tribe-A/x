package com.share.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageTokenDto {
    private String token;
    private String fileName;
}
