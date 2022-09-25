package com.share.service.authentication.service.post;

import com.alibaba.fastjson.JSONArray;
import com.share.common.pojo.dao.PostsInfo;
import com.share.common.pojo.dto.Post;
import com.share.dao.PostsInfoMapper;
import com.share.service.authentication.uid.UidGenerator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private PostsInfoMapper postsInfoMapper;
    private UidGenerator uidGenerator;
    private static final String prefix = "P";

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
                result.setFilesId(ids);
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

    public List<Post> queryPost() {

        return null;
    }

}
