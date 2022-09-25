package com.share.dao;

import com.share.common.pojo.dao.DewormInfoDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeWormMapper {
    Integer deleteByPrimaryKey(Long id);

    Integer deleteByDeWormKey(DewormInfoDo record);

    Integer insert(DewormInfoDo record);

    Integer insertSelective(DewormInfoDo record);

    List<DewormInfoDo> selectByPetKey(String petId);

    DewormInfoDo selectByDeWormKey(String deWormId);

    Integer updateByDeWormKeySelective(DewormInfoDo record);

    Integer updateByDeWormKey(DewormInfoDo record);
}
