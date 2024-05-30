package cn.iocoder.yudao.module.system.service.arbi.sourceapplicantinfo;

import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.validation.Valid;

/**
 * 申请方的信息表 Service 接口
 *
 * @author 仲裁管理员
 */
public interface SourceApplicantInfoService {

    /**
     * 创建申请方的信息表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSourceApplicantInfo(@Valid SourceApplicantInfoSaveReqVO createReqVO);

    /**
     * 更新申请方的信息表
     *
     * @param updateReqVO 更新信息
     */
    void updateSourceApplicantInfo(@Valid SourceApplicantInfoSaveReqVO updateReqVO);

    /**
     * 删除申请方的信息表
     *
     * @param id 编号
     */
    void deleteSourceApplicantInfo(Integer id);

    /**
     * 获得申请方的信息表
     *
     * @param id 编号
     * @return 申请方的信息表
     */
    SourceApplicantInfoDO getSourceApplicantInfo(Integer id);

    /**
     * 获得申请方的信息表分页
     *
     * @param pageReqVO 分页查询
     * @return 申请方的信息表分页
     */
    PageResult<SourceApplicantInfoDO> getSourceApplicantInfoPage(SourceApplicantInfoPageReqVO pageReqVO);

    SourceApplicantInfoRespVO covertToSourceApplicantInfoRespVO(SourceApplicantInfoDO sourceApplicantInfo);

    void syncToArbi(Integer id) throws Exception;
}