package cn.iocoder.yudao.module.system.service.arbi.wordtemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplatePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.wordtemplate.WordTemplateDO;

import javax.validation.Valid;


/**
 * 申请人模板配置 Service 接口
 *
 * @author 仲裁管理员
 */
public interface WordTemplateService {

    /**
     * 创建申请人模板配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createWordTemplate(@Valid WordTemplateSaveReqVO createReqVO);

    /**
     * 更新申请人模板配置
     *
     * @param updateReqVO 更新信息
     */
    void updateWordTemplate(@Valid WordTemplateSaveReqVO updateReqVO);

    /**
     * 删除申请人模板配置
     *
     * @param id 编号
     */
    void deleteWordTemplate(Integer id);

    /**
     * 获得申请人模板配置
     *
     * @param id 编号
     * @return 申请人模板配置
     */
    WordTemplateDO getWordTemplate(Integer id);
    WordTemplateRespVO getWordTemplateInfo(Integer id) throws Exception;

    /**
     * 获得申请人模板配置分页
     *
     * @param pageReqVO 分页查询
     * @return 申请人模板配置分页
     */
    PageResult<WordTemplateRespVO> getWordTemplatePage(WordTemplatePageReqVO pageReqVO);
//    PageResult<WordTemplateDO> getWordTemplatePage(WordTemplatePageReqVO pageReqVO);

}