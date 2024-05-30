package cn.iocoder.yudao.module.system.service.arbi.sourceapplicantinfo;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.annotation.SeaWeedUrlReplacement;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoSaveReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SyncToArbiReqVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.vo.ArbiSystemResponseVo;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.sourceapplicantinfo.SourceApplicantInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Value;


import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.SOURCE_APPLICANT_INFO_NOT_EXISTS;

/**
 * 申请方的信息表 Service 实现类
 *
 * @author 仲裁管理员
 */
@Service
@Validated
@Slf4j
public class SourceApplicantInfoServiceImpl implements SourceApplicantInfoService {
    @Value("${arbitrationService.syncApplicantInfoUrl}")
    private String syncApplicantInfoUrl;

    @Resource
    private SourceApplicantInfoMapper sourceApplicantInfoMapper;

    @Override
    public Integer createSourceApplicantInfo(SourceApplicantInfoSaveReqVO createReqVO) {
        // 插入
        SourceApplicantInfoDO sourceApplicantInfo = BeanUtils.toBean(createReqVO, SourceApplicantInfoDO.class);
        sourceApplicantInfoMapper.insert(sourceApplicantInfo);
        // 返回
        return sourceApplicantInfo.getId();
    }

    @Override
    public void updateSourceApplicantInfo(SourceApplicantInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateSourceApplicantInfoExists(updateReqVO.getId());
        // 更新
        SourceApplicantInfoDO updateObj = BeanUtils.toBean(updateReqVO, SourceApplicantInfoDO.class);
        sourceApplicantInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteSourceApplicantInfo(Integer id) {
        // 校验存在
        validateSourceApplicantInfoExists(id);
        // 删除
        sourceApplicantInfoMapper.deleteById(id);
    }

    private void validateSourceApplicantInfoExists(Integer id) {
        if (sourceApplicantInfoMapper.selectById(id) == null) {
            throw exception(SOURCE_APPLICANT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public SourceApplicantInfoDO getSourceApplicantInfo(Integer id) {
        return sourceApplicantInfoMapper.selectById(id);
    }

    @Override
    public PageResult<SourceApplicantInfoDO> getSourceApplicantInfoPage(SourceApplicantInfoPageReqVO pageReqVO) {
        return sourceApplicantInfoMapper.selectPage(pageReqVO);
    }

    @Override
    @SeaWeedUrlReplacement
    public SourceApplicantInfoRespVO covertToSourceApplicantInfoRespVO(SourceApplicantInfoDO sourceApplicantInfo) {
        return BeanUtils.toBean(sourceApplicantInfo, SourceApplicantInfoRespVO.class);
    }

    @Override
    public void syncToArbi(Integer id) throws Exception{
        SourceApplicantInfoDO sourceApplicantInfoDO = sourceApplicantInfoMapper.selectById(id);
        if(sourceApplicantInfoDO == null) {
            throw new Exception("id错误， 没有此纪录");
        }

        SyncToArbiReqVo reqVo = convertToSyncToArbiReqVo(sourceApplicantInfoDO);

        String paramJson = "";
        try{
            paramJson = JSONUtil.toJsonStr(reqVo);
            System.out.println("同步申请人给仲裁系统， 参数： "+paramJson);
            log.info("同步申请人给仲裁系统， 参数： {}", paramJson);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        String result = HttpUtil.post(syncApplicantInfoUrl, paramJson);
        log.info("同步申请人给仲裁系统， 返回： {}", result);
        System.out.println("同步申请人给仲裁系统， 返回： "+result);

        ArbiSystemResponseVo responseVo = JSONUtil.toBean(result, ArbiSystemResponseVo.class);

        if(responseVo.getCode() != 0) {
            throw new Exception(responseVo.getMsg());
        }

        sourceApplicantInfoDO.setSyncFlag(1);
        sourceApplicantInfoMapper.updateById(sourceApplicantInfoDO);

    }

    private SyncToArbiReqVo convertToSyncToArbiReqVo(SourceApplicantInfoDO sourceApplicantInfoDO){
        SyncToArbiReqVo reqVo = new SyncToArbiReqVo();
        reqVo.setParkingpaySystemId(sourceApplicantInfoDO.getId());
        reqVo.setName(sourceApplicantInfoDO.getApplicant());
        reqVo.setSex(sourceApplicantInfoDO.getSex());
        reqVo.setType(sourceApplicantInfoDO.getType());
        reqVo.setIdentification(sourceApplicantInfoDO.getIdentification());
        reqVo.setIdentificationUrl(sourceApplicantInfoDO.getIdentificationImgUrl());
        reqVo.setBusinessLicenseUrl(sourceApplicantInfoDO.getBusinessLicenseUrl());
        reqVo.setLegalRepresentativeIdUrl(sourceApplicantInfoDO.getLegalPersonImgUrl());
        reqVo.setLegalRepresentativeIdCertificateUrl(sourceApplicantInfoDO.getLegalPersonIdCertificateUrl());
        reqVo.setAuthorizationLetterUrl(sourceApplicantInfoDO.getAuthorizationLetterUrl());
        reqVo.setLawFirmLetterUrl(sourceApplicantInfoDO.getLawFirmLetterUrl());
        reqVo.setLawyerLicenseUrl(sourceApplicantInfoDO.getLawyerLicenseUrl());
        reqVo.setAddress(sourceApplicantInfoDO.getAddress());
        reqVo.setPhone(sourceApplicantInfoDO.getPhone());
        return  reqVo;
    }
}
