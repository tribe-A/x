package com.share.web.app.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PetVo {
    private Date createTime;
    private String petId;
    private String petName;
    private String petMasterId;
    private String petMatherId;
    private String petFatherId;
    private Double petWeight;
    private Date petBirthDay;
    private PetVo petMather;
    private PetVo petFather;
}
