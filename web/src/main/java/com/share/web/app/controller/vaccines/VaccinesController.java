package com.share.web.app.controller.vaccines;

import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.dto.DeWormDto;
import com.share.common.pojo.dto.Vaccine;
import com.share.service.authentication.service.vaccines.VaccinesService;
import com.share.web.app.auth.ReplaceUserId;
import com.share.web.app.pojo.request.vaccine.VaccineRequest;
import com.share.web.app.pojo.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/vaccines")
@AllArgsConstructor
public class VaccinesController {

    private VaccinesService vaccinesService;

    @ReplaceUserId
    @PostMapping("/add")
    public BaseResponse addVaccine(@RequestBody VaccineRequest request) {
        Vaccine vaccine = new Vaccine();
        BeanUtils.copyProperties(request,vaccine);
        boolean success = vaccinesService.addVaccine(vaccine, request.getUserId());
        return BaseResponse.response(success,null,"添加成功","添加失败");
    }

    @ReplaceUserId
    @PostMapping("/query")
    public BaseResponse queryVaccines(@RequestBody VaccineRequest request) {
        Vaccine vaccine = new Vaccine();
        vaccine.setPetId(request.getPetId());
        PageNumber pageNumber = new PageNumber(request.getPageNum(),request.getPageSize());
        PageInfo<Vaccine> vaccinePageInfo = vaccinesService.queryVaccines(vaccine, request.getUserId(), pageNumber);
        return BaseResponse.response(true,vaccinePageInfo,"添加成功","添加失败");
    }

    @ReplaceUserId
    @PostMapping("/update")
    public BaseResponse updateVaccine(@RequestBody VaccineRequest request) {
        Vaccine vaccine = new Vaccine();
        BeanUtils.copyProperties(request,vaccine);
        Vaccine result = vaccinesService.updateVaccine(vaccine, request.getUserId());
        return BaseResponse.response(Objects.nonNull(result),result,"更新成功","更新失败");
    }
    @ReplaceUserId
    @GetMapping("/del")
    public BaseResponse deleteVaccine(VaccineRequest request) {
        Vaccine vaccine = new Vaccine();
        BeanUtils.copyProperties(request,vaccine);
        boolean success = vaccinesService.delVaccine(vaccine, request.getUserId());
        return BaseResponse.response(success,request.getVaccineId(),"删除成功","删除失败");
    }
}
