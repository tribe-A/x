package com.share.common.pojo.dao;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * posts_info
 * @author
 */
@Data
public class PostsInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * ID
     */
    private String postId;

    /**
     * 作者ID
     */
    private String authorId;

    /**
     * 宠物ID
     */
    private String petId;

    /**
     * 简介
     */
    private String introduction;

    private String filesId;
    /**
     * 状态
     */
    private Byte status;

    private Byte isDelete;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
