package cn.iocoder.yudao.module.system.dal.mysql.arbi.cases;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CasePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.cases.CaseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 案件 Mapper
 *
 * @author 仲裁管理员
 */
@Mapper
public interface CaseMapper extends BaseMapperX<CaseDO>{

    default PageResult<CaseDO> selectPage(CasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CaseDO>()
                .eqIfPresent(CaseDO::getSyncStatus, reqVO.getSyncStatus())
                .eqIfPresent(CaseDO::getBusType, reqVO.getBusType())
                .likeIfPresent(CaseDO::getRespondentName, reqVO.getRespondentName())
                .eqIfPresent(CaseDO::getRespondentPhone, reqVO.getRespondentPhone())
                .betweenIfPresent(CaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CaseDO::getId));
    }

}