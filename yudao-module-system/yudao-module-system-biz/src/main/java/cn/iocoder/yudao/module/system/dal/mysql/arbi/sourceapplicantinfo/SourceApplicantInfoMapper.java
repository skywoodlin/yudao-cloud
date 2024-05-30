package cn.iocoder.yudao.module.system.dal.mysql.arbi.sourceapplicantinfo;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.util.StringUtils;

/**
 * 申请方的信息表 Mapper
 *
 * @author 仲裁管理员
 */
@Mapper
public interface SourceApplicantInfoMapper extends BaseMapperX<SourceApplicantInfoDO>{

    default PageResult<SourceApplicantInfoDO> selectPage(SourceApplicantInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SourceApplicantInfoDO>()
                .eqIfPresent(SourceApplicantInfoDO::getApplicant, StringUtils.isEmpty(reqVO.getApplicant())?null:
                        reqVO.getApplicant().trim())
                .eqIfPresent(SourceApplicantInfoDO::getIdentification, StringUtils.isEmpty(reqVO.getIdentification())?null:
                        reqVO.getIdentification().trim())
                .likeIfPresent(SourceApplicantInfoDO::getLegalPersonName, StringUtils.isEmpty(reqVO.getLegalPersonName())?null:
                        reqVO.getLegalPersonName().trim())
                .eqIfPresent(SourceApplicantInfoDO::getLegalPersonId, StringUtils.isEmpty(reqVO.getLegalPersonId())?null:
                        reqVO.getLegalPersonId().trim())
                .eqIfPresent(SourceApplicantInfoDO::getPhone, StringUtils.isEmpty(reqVO.getPhone())?null:reqVO.getPhone().trim())
                .betweenIfPresent(SourceApplicantInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SourceApplicantInfoDO::getId));
    }

}
