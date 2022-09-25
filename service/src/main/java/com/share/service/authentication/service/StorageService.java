package com.share.service.authentication.service;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.share.common.pojo.constant.FileStatus;
import com.share.common.pojo.dao.FileInfo;
import com.share.common.pojo.dto.FileCallBack;
import com.share.common.pojo.dto.StorageTokenDto;
import com.share.dao.FileInfoMapper;
import com.share.service.authentication.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class StorageService {

    @Autowired
    private Auth auth;
    @Autowired
    private UidGenerator uidGenerator;
    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Value("${qiniu.bucket}")
    private  String bucket;

    private static final ConcurrentHashMap tokenCacheMap = new ConcurrentHashMap();

    public StorageTokenDto uploadToken(String file,String userId) {
        String fileName = String.valueOf(uidGenerator.getUID());
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "https://16y1b65572.goho.co/share/storage/callback");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"bucket\":\"$(bucket)\",\"mimeType\":$(mimeType)}");
        putPolicy.put("callbackBodyType", "application/json");
        if (file.contains(".")) {
            String fileSuffix = StringUtils.substringAfterLast(file, ".");
            fileName = fileName + "."+ fileSuffix;
        }
        String token = auth.uploadToken(bucket,fileName,3600,putPolicy);
        try {
            tokenCacheMap.put(fileName,userId);
        }catch (Exception e) {
            log.info("缓存异常:",e);
        }
        return new StorageTokenDto(token,fileName);
    }


    public boolean callback(FileCallBack callbackBody, String callbackAuthHeader) {
        boolean validCallback = auth.isValidCallback(callbackAuthHeader, "https://16y1b65572.goho.co/share/storage/callback", callbackBody.toString().getBytes(), "application/json");
        if (validCallback) {
            String uploadUser = (String) tokenCacheMap.get(callbackBody.getKey());
            Object remove = tokenCacheMap.remove(callbackBody.getKey());
            log.info("删除内容:{}",remove);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileKey(callbackBody.getKey());
            fileInfo.setBucket(callbackBody.getBucket());
            fileInfo.setMineType(callbackBody.getMimeType());
            fileInfo.setUpdateUser(uploadUser);
            fileInfo.setCreateUser(uploadUser);
            fileInfo.setCreateTime(new Date());
            fileInfo.setUpdateTime(new Date());
            fileInfo.setIsDelete(0);
            fileInfo.setStatus(FileStatus.DEFAULT.getStatus());
            Integer effectSize = fileInfoMapper.insert(fileInfo);
            if (Objects.isNull(effectSize) || effectSize < 1) {
                return false;
            }
        }
        return validCallback;
    }

    public String fileDownloadUrl(String fileKey) {
        
    }
}