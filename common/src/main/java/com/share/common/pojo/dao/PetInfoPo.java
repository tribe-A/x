package com.share.common.pojo.dao;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class PetInfoPo extends BaseInfo{
    private String petId;
    private String petName;
    private String avatar;
    private String petMasterId;
    private String petMatherId;
    private String petFatherId;
    private Double petWeight;
    private Date petBirthDay;
    private Integer petStatus;
}
