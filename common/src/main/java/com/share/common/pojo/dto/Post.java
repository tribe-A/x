package com.share.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    private String postId;
    private String petId;
    private String authorId;
    private String introduction;
    private List<String> filesId;
    private Date createTime;
}
