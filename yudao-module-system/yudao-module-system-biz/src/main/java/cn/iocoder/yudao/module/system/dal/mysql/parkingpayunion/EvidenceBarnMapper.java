package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdReqVo;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.EvidenceBarn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvidenceBarnMapper extends BaseMapperX<EvidenceBarn>{

    default PageResult<EvidenceBarn> selectPage(GetEvidenceBySourceIdReqVo reqVO){
        return selectPage(reqVO, new LambdaQueryWrapperX<EvidenceBarn>()
                .eqIfPresent(EvidenceBarn::getSourceId, reqVO.getSourceId())
                .likeIfPresent(EvidenceBarn::getPlateNum, reqVO.getPlateNum())
                .betweenIfPresent(EvidenceBarn::getEviTime, reqVO.getEviTime())
                .eqIfNotBlank(EvidenceBarn::getThirdpartyOrderId, reqVO.getThirdpartyOrderId())
                .orderByDesc(EvidenceBarn::getEviTime));
    }
}
