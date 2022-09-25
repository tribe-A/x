package com.share.dao;

import com.share.common.pojo.dao.PetInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetInfoMapper {
    Integer addPet(PetInfoPo pet);
    Integer deletePet(@Param("petId") String petId);
    Integer updatePet(PetInfoPo pet);
    PetInfoPo selectPetById(@Param("petId")String petId,@Param("petMasterId")String petMasterId);
    List<PetInfoPo> selectPetByUserId(String userId);
    Integer selectPetStatusById(String petId);
    Integer selectIsPetMaster(String userId,String petId);

}
