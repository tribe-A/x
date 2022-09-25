package com.share.web.app.pojo.request.post;

import com.share.web.app.pojo.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostRequest extends BaseRequest {


    private String postId;

    private String petId;

    /**
     *描述
     */
    private String introduction;
    /**
     * 文件ID列表
     */
    private List<String> filesId;
}
