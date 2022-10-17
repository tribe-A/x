package com.share.dao;

import com.share.common.pojo.dao.PostsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostsInfoMapper {
    int deleteByPostKey(PostsInfo postsInfo);

    int insert(PostsInfo record);

    int insertSelective(PostsInfo record);

    PostsInfo selectByPostKey(String id);

    int updateByPrimaryKeySelective(PostsInfo record);

    int updateByPrimaryKey(PostsInfo record);

    List<PostsInfo> selectByPetId(@Param("userId") String userId,@Param("petId") String petId);
}
