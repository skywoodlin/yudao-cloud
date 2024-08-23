package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ChannelDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */
@Mapper
public interface ChannelMapper extends BaseMapperX<ChannelDO>{

    default PageResult<ChannelDO> selectPage(ChannelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChannelDO>()
                .eqIfPresent(ChannelDO::getAppid, reqVO.getAppid())
                .likeIfPresent(ChannelDO::getChannelName, reqVO.getChannelName())
                .betweenIfPresent(ChannelDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChannelDO::getMchId, reqVO.getMchId())
                .orderByDesc(ChannelDO::getId));
    }
}
