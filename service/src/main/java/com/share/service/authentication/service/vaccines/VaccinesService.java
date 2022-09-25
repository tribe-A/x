package com.share.service.authentication.service.vaccines;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.constant.PetStatus;
import com.share.common.pojo.dao.VaccinesInfo;
import com.share.common.pojo.dto.Vaccine;
import com.share.dao.VaccinesInfoMapper;
import com.share.service.authentication.service.pet.PetService;
import com.share.service.authentication.uid.UidGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VaccinesService {

    private VaccinesInfoMapper vaccinesInfoMapper;
    private PetService petService;
    private UidGenerator uidGenerator;
    private static final String prefix = "V";

    public boolean addVaccine(Vaccine vaccine, String userId) {
        String vaccineId = prefix + uidGenerator.getUID();
        VaccinesInfo vaccinesInfo = new VaccinesInfo();
        BeanUtils.copyProperties(vaccine,vaccinesInfo);
        vaccinesInfo.setCreateUser(userId);
        vaccinesInfo.setUpdateUser(userId);
        vaccinesInfo.setVaccineId(vaccineId);
        int insertSize = vaccinesInfoMapper.insert(vaccinesInfo);
        return insertSize > 0;
    }

    public boolean delVaccine(Vaccine vaccine, String userId) {
        Assert.isTrue(petService.isPetMaster(userId, vaccine.getPetId()),"删除失败");
        VaccinesInfo vaccinesInfo = new VaccinesInfo();
        vaccinesInfo.setVaccineId(vaccine.getVaccineId());
        vaccinesInfo.setIsDelete(1);
        int delSize = vaccinesInfoMapper.updateByVaccineSelective(vaccinesInfo);
        return delSize > 0;
    }

    public PageInfo<Vaccine> queryVaccines(Vaccine vaccine, String userId, PageNumber pageNumber) {
        Assert.isTrue(petService.petStatus(vaccine.getPetId()) == PetStatus.SHARE.getStatus() ||petService.isPetMaster(userId, vaccine.getPetId()),"查询失败");
        PageHelper.startPage(pageNumber.getPageNum(),pageNumber.getPageSize());
        VaccinesInfo vaccinesInfo = new VaccinesInfo();
        vaccinesInfo.setVaccineId(vaccine.getVaccineId());
        vaccinesInfo.setPetId(vaccine.getPetId());
        List<VaccinesInfo> vaccineList = vaccinesInfoMapper.selectByPetKey(vaccinesInfo);
        List<Vaccine> result = vaccineList.stream().map(i -> {
            Vaccine v = new Vaccine();
            BeanUtils.copyProperties(i, v);
            return v;
        }).collect(Collectors.toList());
        return new PageInfo<>(result);
    }

    public Vaccine updateVaccine(Vaccine vaccine, String userId) {
        Assert.isTrue(petService.isPetMaster(userId, vaccine.getPetId()),"更新失败");
        VaccinesInfo vaccinesInfo = new VaccinesInfo();
        BeanUtils.copyProperties(vaccine,vaccinesInfo);
        vaccinesInfo.setCreateUser(userId);
        vaccinesInfo.setUpdateUser(userId);
        int effectSize = vaccinesInfoMapper.updateByVaccineSelective(vaccinesInfo);
        if (effectSize > 0) {
            VaccinesInfo updateInfo = vaccinesInfoMapper.selectByVaccineKey(vaccinesInfo);
            Vaccine result = new Vaccine();
            BeanUtils.copyProperties(updateInfo,result);
            return result;
        }
        return null;
    }
}
