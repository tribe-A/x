package com.share.web.app.controller;


import com.share.common.pojo.dto.FileCallBack;
import com.share.common.pojo.dto.StorageTokenDto;
import com.share.service.authentication.service.StorageService;
import com.share.web.app.auth.ReplaceUserId;
import com.share.web.app.pojo.request.StorageRequest;
import com.share.web.app.pojo.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/key")
    @ReplaceUserId
    public BaseResponse storageKey(@RequestBody StorageRequest request) {
        StorageTokenDto storageTokenDto = storageService.uploadToken(request.getFileName(),request.getUserId());
        return BaseResponse.response(true,storageTokenDto,"获取token成功","服务异常");
    }

    @PostMapping("/callback")
    public BaseResponse callback(@RequestBody FileCallBack callbackBody, @RequestHeader(value = "Authorization") String  auth) {
        log.info("callback回调内容:{}",callbackBody.toString());
        boolean correctInfo = storageService.callback(callbackBody,auth);
        return BaseResponse.response(correctInfo,callbackBody,"文件上传成功","文件上传失败");
    }

}
