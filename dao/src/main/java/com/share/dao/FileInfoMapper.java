package com.share.dao;

import com.share.common.pojo.dao.FileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileInfoMapper {
    Integer deleteByPrimaryKey(Long id);

    Integer insert(FileInfo record);

    Integer insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    FileInfo selectByFileKey(String fileKey);

    Integer fileNum(FileInfo record);

    Integer updateByPrimaryKeySelective(FileInfo record);

    Integer updateByPrimaryKey(FileInfo record);
}