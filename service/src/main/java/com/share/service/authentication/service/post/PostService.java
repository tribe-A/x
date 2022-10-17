package com.share.service.authentication.service.post;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.dao.PostsInfo;
import com.share.common.pojo.dto.PetDto;
import com.share.common.pojo.dto.Post;
import com.share.dao.PostsInfoMapper;
import com.share.service.authentication.service.StorageService;
import com.share.service.authentication.uid.UidGenerator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private PostsInfoMapper postsInfoMapper;
    private UidGenerator uidGenerator;
    private static final String prefix = "P";
    private StorageService storageService;

    public Post addPost(Post post,String userId){
        long uid = uidGenerator.getUID();
        PostsInfo postsInfo = new PostsInfo();
        BeanUtils.copyProperties(post,postsInfo);
        postsInfo.setPostId(prefix + uid);
        postsInfo.setCreateUser(userId);
        postsInfo.setAuthorId(userId);
        postsInfo.setUpdateUser(userId);
        JSONArray fileArray = new JSONArray();
        fileArray.addAll(post.getFilesId());
        postsInfo.setFilesId(fileArray.toJSONString());
        int addSize = postsInfoMapper.insert(postsInfo);
        if(addSize > 0) {
            Post result = new Post();
            PostsInfo pi = postsInfoMapper.selectByPostKey(postsInfo.getPostId());
            BeanUtils.copyProperties(pi,result);
            if (StringUtils.isNotBlank(pi.getFilesId())) {
                JSONArray ids = (JSONArray) JSONArray.parse(pi.getFilesId());
                List<String> urlList = ids.stream().map(id->(String)id).map(storageService::fileDownloadUrl).collect(Collectors.toList());
                result.setFilesId(urlList);
            }
            return result;
        }
        return null;
    }

    public boolean delPost(Post post,String userId) {

        PostsInfo postsInfo = new PostsInfo();
        postsInfo.setAuthorId(userId);
        postsInfo.setPostId(post.getPostId());
        int delSize = postsInfoMapper.deleteByPostKey(postsInfo);
        return delSize > 0;

    }

    public PageInfo<Post> queryPost(String userId, String petId, PageNumber pageNumber) {
        PageHelper.startPage(pageNumber.getPageNum(),pageNumber.getPageSize());
        List<PostsInfo> list = postsInfoMapper.selectByPetId(userId, petId);
        List<Post> data = list.stream().map(p -> {
            Post post = new Post();
            BeanUtils.copyProperties(p, post);
            if (p.getFilesId() != null) {
                JSONArray array = (JSONArray) JSONArray.parse(p.getFilesId());
                List<String> filesId = array.stream().map(String::valueOf).map(storageService::fileDownloadUrl).collect(Collectors.toList());
                post.setFilesId(filesId);
            }
            return post;
        }).collect(Collectors.toList());
        PageInfo<Post> result = new PageInfo<>(data);
        return result;
    }

}
