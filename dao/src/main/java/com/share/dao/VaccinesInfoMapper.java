package com.share.dao;


import com.share.common.pojo.dao.VaccinesInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VaccinesInfoMapper {
    int deleteByVaccineKey(String vaccineId);

    int insert(VaccinesInfo record);

    int insertSelective(VaccinesInfo record);

    VaccinesInfo selectByVaccineKey(VaccinesInfo vaccinesInfo);

    List<VaccinesInfo> selectByPetKey(VaccinesInfo vaccinesInfo);


    int updateByVaccineSelective(VaccinesInfo record);

    int updateByVaccineKey(VaccinesInfo record);
}
