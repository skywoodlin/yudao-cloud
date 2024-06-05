package cn.iocoder.yudao.module.system.service.arbi.cases;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CasePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.cases.CaseDO;

import javax.validation.Valid;


/**
 * 案件 Service 接口
 *
 * @author 仲裁管理员
 */
public interface CaseService {

    /**
     * 创建案件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCase(@Valid CaseSaveReqVO createReqVO);

    /**
     * 更新案件
     *
     * @param updateReqVO 更新信息
     */
    void updateCase(@Valid CaseSaveReqVO updateReqVO);

    /**
     * 删除案件
     *
     * @param id 编号
     */
    void deleteCase(Integer id);

    /**
     * 获得案件
     *
     * @param id 编号
     * @return 案件
     */
    CaseDO getCase(Integer id);

    /**
     * 获得案件分页
     *
     * @param pageReqVO 分页查询
     * @return 案件分页
     */
//    PageResult<CaseDO> getCasePage(CasePageReqVO pageReqVO);
    PageResult<CaseRespVO> getCasePage(CasePageReqVO pageReqVO);

    /**
     * 同步案件信息到仲裁系统
     * @param id
     */
    void syncToArbi(Integer id) throws Exception;

    CaseRespVO getCaseInfo(Integer id) throws Exception;
}