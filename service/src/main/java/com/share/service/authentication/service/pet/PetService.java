package com.share.service.authentication.service.pet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.constant.PetStatus;
import com.share.common.pojo.dao.PetInfoPo;
import com.share.common.pojo.dto.PetDto;
import com.share.dao.PetInfoMapper;
import com.share.service.authentication.service.common.FileService;
import com.share.service.authentication.uid.UidGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PetService {

    private static final String prefix = "";

    private PetInfoMapper petInfoMapper;
    private UidGenerator uidGenerator;
    private FileService fileService;


    public PageInfo<PetDto> findPetListByUserId(String userId, PageNumber pageNumber) {
        PageHelper.startPage(pageNumber.getPageNum(),pageNumber.getPageSize());
        List<PetInfoPo> petList = petInfoMapper.selectPetByUserId(userId);
        if (Objects.isNull(petList)) {
            return null;
        }
        List<PetDto> data = petList.stream().map(p -> {
            PetDto petDto = new PetDto();
            BeanUtils.copyProperties(p, petDto);
            return petDto;
        }).collect(Collectors.toList());
        PageInfo<PetDto> result = new PageInfo<>(data);
        return result;

    }

    public PetDto findPetByPetId(String petId,String userId) {
        PetDto result = new PetDto();
        if (StringUtils.isNotBlank(petId)) {
            PetInfoPo petInfoPo = petInfoMapper.selectPetById(petId,userId);
            if (Objects.isNull(petInfoPo)) {
                return null;
            }
            BeanUtils.copyProperties(petInfoPo,result);
        }
        return result;
    }

    public boolean addPet(PetDto pet,String userId) {
        long uid = uidGenerator.getUID();
        log.info("获取到ID:{}",uid);
        PetInfoPo petInfo = new PetInfoPo();
        BeanUtils.copyProperties(pet,petInfo);
        petInfo.setPetMasterId(userId);
        petInfo.setCreateUser(userId);
        petInfo.setUpdateUser(userId);
        petInfo.setPetId(prefix+uid);
        petInfo.setPetStatus(PetStatus.SHARE.getStatus());
        petInfo.setIsDelete(0);
        if (!fileService.fileKeyValid(pet.getAvatar(), userId)) {
            petInfo.setAvatar(null);
        }
        Integer effectSize = petInfoMapper.addPet(petInfo);
        if (Objects.nonNull(effectSize) && effectSize > 0) {
            return true;
        }
        return false;
    }

    public boolean updatePet(PetDto pet) {
        PetInfoPo petInfo = new PetInfoPo();
        BeanUtils.copyProperties(pet,petInfo);
        Integer effectSize = petInfoMapper.updatePet(petInfo);
        if (Objects.nonNull(effectSize) && effectSize > 0) {
            return true;
        }
        return false;
    }

    public int petStatus(String petId) {
        Integer petStatus = petInfoMapper.selectPetStatusById(petId);
        if(Objects.isNull(petStatus)) {
            return PetStatus.OWN.getStatus();
        }
        return petStatus;
    }

    public boolean isPetMaster(String userId,String petId) {
        Integer petSize = petInfoMapper.selectIsPetMaster(userId, petId);
        return petSize > 0;
    }
}
