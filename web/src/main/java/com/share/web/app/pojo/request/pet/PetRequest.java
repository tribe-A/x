package com.share.web.app.pojo.request.pet;

import com.share.web.app.pojo.request.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class PetRequest extends BaseRequest {
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
