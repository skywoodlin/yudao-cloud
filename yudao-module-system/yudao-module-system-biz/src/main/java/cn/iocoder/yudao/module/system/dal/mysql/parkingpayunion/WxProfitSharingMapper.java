package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.WxProfitSharing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxProfitSharingMapper extends BaseMapperX<WxProfitSharing> {

        default PageResult<WxProfitSharing> selectPage(GetWXProfitSharingReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WxProfitSharing>()
                .eqIfPresent(WxProfitSharing::getOutOrderNo, reqVO.getOutOrderNo())
                .eqIfPresent(WxProfitSharing::getOrderCode, reqVO.getOrderCode())
                .betweenIfPresent(WxProfitSharing::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WxProfitSharing::getId));
    }
}
