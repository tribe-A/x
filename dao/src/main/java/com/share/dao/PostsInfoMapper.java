package com.share.dao;

import com.share.common.pojo.dao.PostsInfo;

public interface PostsInfoMapper {
    int deleteByPostKey(PostsInfo postsInfo);

    int insert(PostsInfo record);

    int insertSelective(PostsInfo record);

    PostsInfo selectByPostKey(String id);

    int updateByPrimaryKeySelective(PostsInfo record);

    int updateByPrimaryKey(PostsInfo record);
}
