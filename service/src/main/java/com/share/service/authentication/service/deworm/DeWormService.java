package com.share.service.authentication.service.deworm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.common.pojo.common.PageNumber;
import com.share.common.pojo.constant.PetStatus;
import com.share.common.pojo.dao.DewormInfoDo;
import com.share.common.pojo.dto.DeWormDto;
import com.share.dao.DeWormMapper;
import com.share.service.authentication.service.pet.PetService;
import com.share.service.authentication.uid.UidGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DeWormService {

    private DeWormMapper deWormMapper;
    private PetService petService;
    private UidGenerator uidGenerator;
    private static final String prefix = "D";

    public DeWormDto addDeWormRecord(DeWormDto deWormRecord,String userId) {
        String deWormId = prefix + uidGenerator.getUID();
        DewormInfoDo dewormInfoDo = new DewormInfoDo();
        BeanUtils.copyProperties(deWormRecord, dewormInfoDo);
        dewormInfoDo.setCreateUser(userId);
        dewormInfoDo.setUpdateUser(userId);
        dewormInfoDo.setDewormId(deWormId);
        dewormInfoDo.setCreateTime(new Date());
        dewormInfoDo.setUpdateTime(new Date());
        Integer effectSize = deWormMapper.insert(dewormInfoDo);
        if(Objects.nonNull(effectSize) && effectSize > 0) {
            DewormInfoDo addRecord = deWormMapper.selectByDeWormKey(deWormId);
            DeWormDto result = new DeWormDto();
            BeanUtils.copyProperties(addRecord, result);
            return result;
        }
        return null;
    }

    public boolean delDeWormRecord(DeWormDto deWormRecord,String userId) {
        Assert.isTrue(petService.isPetMaster(userId, deWormRecord.getPetId()),"添加失败");
        DewormInfoDo dewormInfoDo = new DewormInfoDo();
        dewormInfoDo.setDewormId(deWormRecord.getDewormId());
        dewormInfoDo.setCreateUser(userId);
        Integer effectSize = deWormMapper.deleteByDeWormKey(dewormInfoDo);
        return Objects.nonNull(effectSize) && effectSize > 0;
    }

    public PageInfo<DeWormDto> queryDeWormRecord(DeWormDto deWormRecord, String userId, PageNumber pageNumber) {
        Assert.isTrue(petService.petStatus(deWormRecord.getPetId()) == PetStatus.SHARE.getStatus() ||petService.isPetMaster(userId, deWormRecord.getPetId()),"查询失败");
        List<DeWormDto> data = new ArrayList<>();
        PageHelper.startPage(pageNumber.getPageNum(),pageNumber.getPageSize());
        List<DewormInfoDo> deWormInfoDoList = deWormMapper.selectByPetKey(deWormRecord.getPetId());
        if (Objects.nonNull(deWormInfoDoList)) {
            for (DewormInfoDo p : deWormInfoDoList) {
                DeWormDto deWormDto = new DeWormDto();
                BeanUtils.copyProperties(p,deWormDto);
                data.add(deWormDto);
            }
        }
        PageInfo<DeWormDto> deWormPageInfo = new PageInfo<DeWormDto>(data);
        return deWormPageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateDeWormRecord(DeWormDto deWormRecord,String userId) {
        Assert.isTrue(petService.isPetMaster(userId, deWormRecord.getPetId()),"更新失败");
        DewormInfoDo dewormInfoDo = new DewormInfoDo();
        BeanUtils.copyProperties(deWormRecord, dewormInfoDo);
        Integer effectSize = deWormMapper.updateByDeWormKeySelective(dewormInfoDo);
        return Objects.nonNull(effectSize) && effectSize > 0;
    }
}
