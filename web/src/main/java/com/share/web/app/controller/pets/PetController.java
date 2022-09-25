package com.share.web.app.controller.pets;

import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.dto.PetDto;
import com.share.service.authentication.service.pet.PetService;
import com.share.web.app.auth.ReplaceUserId;
import com.share.web.app.pojo.request.pet.PetRequest;
import com.share.web.app.pojo.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Slf4j
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @GetMapping("/queryMyPets")
    @ReplaceUserId
    public BaseResponse findPetListByUserId(PetRequest request) {
        Assert.notNull(request.getUserId(),"请登录后查询");
        log.info("用户:{}",request.getUserId());
        PageNumber pn = new PageNumber(request.getPageNum(),request.getPageSize());
        PageInfo<PetDto> data = petService.findPetListByUserId(request.getUserId(), pn);
        BaseResponse result = BaseResponse.response(true, data, "查询成功", "查询失败");
        return result;
    }

    @GetMapping("/addPets")
    @ReplaceUserId
    public BaseResponse addPets(@RequestBody PetRequest request){
        PetDto petDto = new PetDto();
        BeanUtils.copyProperties(request,petDto);
        boolean success = petService.addPet(petDto,request.getUserId());
        log.info("添加结果:{}",success);
        BaseResponse result = BaseResponse.response(success, null, "查询成功", "查询失败");
        return result;
    }

    @GetMapping("/delPets")
    @ReplaceUserId
    public BaseResponse deletePets(PetRequest request){
        Assert.notNull(request.getPetId(),"ID不能为空");
        PetDto pet = new PetDto();
        pet.setPetId(request.getPetId());
        pet.setPetMasterId(request.getUserId());
        pet.setIsDelete(1);
        boolean success = petService.updatePet(pet);
        BaseResponse result = BaseResponse.response(success, request.getPetId(), "删除成功", "删除失败");
        return result;
    }
    @PostMapping("/updatePets")
    @ReplaceUserId
    public BaseResponse updatePet(@RequestBody PetRequest request) {
        PetDto pet = new PetDto();
        BeanUtils.copyProperties(request,pet);
        pet.setPetMasterId(request.getUserId());
        boolean success = petService.updatePet(pet);
        PetDto resultData = petService.findPetByPetId(pet.getPetId(), request.getUserId());
        BaseResponse result = BaseResponse.response(success, resultData, "查询成功", "查询失败");
        return result;
    }


}
