package com.share.web.app.controller.posts;

import com.share.common.pojo.dto.Post;
import com.share.service.authentication.service.post.PostService;
import com.share.web.app.auth.ReplaceUserId;
import com.share.web.app.pojo.request.post.PostRequest;
import com.share.web.app.pojo.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @PostMapping ("/add")
    @ReplaceUserId
    public BaseResponse addPost(@RequestBody PostRequest request) {
        Post post = new Post();
        BeanUtils.copyProperties(request,post);
        Post result = postService.addPost(post, request.getUserId());
        return BaseResponse.response(Objects.nonNull(result),result,"发布成功","发布失败");
    }
    @GetMapping("/del")
    @ReplaceUserId
    public BaseResponse delPost(PostRequest request) {
        Post post = new Post();
        BeanUtils.copyProperties(request,post);
        boolean delResult = postService.delPost(post, request.getUserId());
        return BaseResponse.response(delResult,null,"删除成功","删除失败");

    }
}
