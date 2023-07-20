package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfitSharingInfoMapper extends BaseMapperX<ProfitSharingInfo> {

        default PageResult<ProfitSharingInfo> selectPage(GetProfitSharingInfoReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProfitSharingInfo>()
                .eqIfPresent(ProfitSharingInfo::getSourceId, reqVO.getSourceId())
                .betweenIfPresent(ProfitSharingInfo::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProfitSharingInfo::getId));
    }
}
