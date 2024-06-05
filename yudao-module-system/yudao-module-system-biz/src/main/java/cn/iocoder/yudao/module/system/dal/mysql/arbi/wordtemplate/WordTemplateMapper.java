package cn.iocoder.yudao.module.system.dal.mysql.arbi.wordtemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplatePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.wordtemplate.WordTemplateDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 申请人模板配置 Mapper
 *
 * @author 仲裁管理员
 */
@Mapper
public interface WordTemplateMapper extends BaseMapperX<WordTemplateDO>{

    default PageResult<WordTemplateDO> selectPage(WordTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WordTemplateDO>()
                .eqIfPresent(WordTemplateDO::getId, reqVO.getId())
                .betweenIfPresent(WordTemplateDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(WordTemplateDO::getTemplateName, reqVO.getTemplateName())
                .orderByDesc(WordTemplateDO::getId));
    }

}