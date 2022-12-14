package com.share.common.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDto {
    private String petId;
    private String petName;
    private String avatar;
    private String petMasterId;
    private String petMatherId;
    private String petMatherName;
    private String petFatherId;
    private String petFatherName;
    private Double petWeight;
    private Date petBirthDay;
    private Integer petStatus;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;
}
