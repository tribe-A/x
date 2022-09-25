package com.share.service.authentication.service.common;

import com.share.common.pojo.constant.FileStatus;
import com.share.common.pojo.dao.FileInfo;
import com.share.dao.FileInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
public class FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    public boolean fileKeyValid(String fileKey,String userId) {
        if (StringUtils.isAnyBlank(fileKey,userId)) {
            return false;
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setStatus(FileStatus.DEFAULT.getStatus());
        fileInfo.setCreateUser(userId);
        fileInfo.setFileKey(fileKey);
        Integer files = fileInfoMapper.fileNum(fileInfo);
        return Objects.nonNull(files) && files >0;
    }

}
