package com.share.web.app.controller.deworm;

import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.dto.DeWormDto;
import com.share.service.authentication.service.deworm.DeWormService;
import com.share.web.app.auth.ReplaceUserId;
import com.share.web.app.pojo.request.deworm.DeWormRequest;
import com.share.web.app.pojo.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/deWorm")
@AllArgsConstructor
public class DeWormController {

    private DeWormService deWormService;

    @ReplaceUserId
    @PostMapping("/add")
    public BaseResponse addDeWormRecord(@RequestBody DeWormRequest request) {
        DeWormDto deWormDto = new DeWormDto();
        BeanUtils.copyProperties(request,deWormDto);
        DeWormDto addRecord = deWormService.addDeWormRecord(deWormDto, request.getUserId());
        return BaseResponse.response(Objects.nonNull(addRecord),addRecord,"添加成功","添加失败");
    }

    @ReplaceUserId
    @GetMapping("/query")
    public BaseResponse queryPetDeWormRecords(DeWormRequest request) {
        DeWormDto deWormDto = new DeWormDto();
        BeanUtils.copyProperties(request,deWormDto);
        PageNumber pageNumber = new PageNumber(request.getPageNum(),request.getPageSize());
        PageInfo<DeWormDto> data = deWormService.queryDeWormRecord(deWormDto, request.getUserId(), pageNumber);
        return BaseResponse.response(true,data,"查询成功","没有查询到结果");
    }

    @ReplaceUserId
    @PostMapping("/update")
    public BaseResponse updateDeWormRecord(@RequestBody DeWormRequest request) {
        DeWormDto deWormDto = new DeWormDto();
        BeanUtils.copyProperties(request,deWormDto);
        boolean updateResult = deWormService.updateDeWormRecord(deWormDto, request.getUserId());
        return BaseResponse.response(updateResult,request.getDewormId(),"更新成功","更新失败");
    }
    @ReplaceUserId
    @GetMapping("/del")
    public BaseResponse deleteDeWormRecord(DeWormRequest request) {
        Assert.notNull(request.getDewormId(),"ID不能为空");
        DeWormDto deWormDto = new DeWormDto();
        deWormDto.setDewormId(request.getDewormId());
        deWormDto.setIsDelete(1);
        boolean updateResult = deWormService.updateDeWormRecord(deWormDto, request.getUserId());
        return BaseResponse.response(updateResult,request.getDewormId(),"删除成功","删除失败");
    }
}
