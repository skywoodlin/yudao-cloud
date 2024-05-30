package cn.iocoder.yudao.module.system.dal.mysql.arbi.businesstype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeOptionsVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.businesstype.BusinessTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */

@Mapper
public interface BusinessTypeMapper extends BaseMapperX<BusinessTypeDO>{

    default PageResult<BusinessTypeDO> selectPage(BusinessTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BusinessTypeDO>()
                .likeIfPresent(BusinessTypeDO::getName, reqVO.getName())
                .eqIfPresent(BusinessTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(BusinessTypeDO::getArbiNeedFile, reqVO.getArbiNeedFile())
                .betweenIfPresent(BusinessTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BusinessTypeDO::getId));
    }

    List<BusinessTypeOptionsVo> getBusinessTypeOptions();

}
