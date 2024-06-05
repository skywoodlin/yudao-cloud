package cn.iocoder.yudao.module.system.service.arbi.cases;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CasePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseSaveReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.SpecificColumnVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.SyncToArbiPotentialCaseVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.vo.ArbiSystemResponseVo;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.cases.CaseDO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.cases.CaseMapper;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.sourceapplicantinfo.SourceApplicantInfoMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ParkingPayUnionMapper;
import cn.iocoder.yudao.module.system.service.arbi.cases.vo.DocInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CASE_NOT_EXISTS;


/**
 * 案件 Service 实现类
 *
 * @author 仲裁管理员
 */
@Service
@Validated
@Slf4j
public class CaseServiceImpl implements CaseService{

    @Value("${arbitrationService.syncPotentialCaseUrl}")
    private String syncPotentialCaseUrl;

    @Resource
    private CaseMapper caseMapper;

    @Resource
    private ParkingPayUnionMapper parkingPayUnionMapper;

    @Resource
    private SourceApplicantInfoMapper applicantInfoMapper;

    @Override
    public Integer createCase(CaseSaveReqVO createReqVO){
        // 插入
        CaseDO cases = BeanUtils.toBean(createReqVO, CaseDO.class);
        caseMapper.insert(cases);
        // 返回
        return cases.getId();
    }

    @Override
    public void updateCase(CaseSaveReqVO updateReqVO){
        // 校验存在
        validateCaseExists(updateReqVO.getId());
        // 更新
        CaseDO updateObj = BeanUtils.toBean(updateReqVO, CaseDO.class);
        caseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCase(Integer id){
        // 校验存在
        validateCaseExists(id);
        // 删除
        caseMapper.deleteById(id);
    }

    private void validateCaseExists(Integer id){
        if(caseMapper.selectById(id) == null){
            throw exception(CASE_NOT_EXISTS);
        }
    }

    @Override
    public CaseDO getCase(Integer id){
        return caseMapper.selectById(id);
    }

    @Override
//    public PageResult<CaseDO> getCasePage(CasePageReqVO pageReqVO) {
    public PageResult<CaseRespVO> getCasePage(CasePageReqVO pageReqVO){
        PageResult<CaseDO> caseDoPageTemp = caseMapper.selectPage(pageReqVO);
        List<CaseDO> caseDOList = caseDoPageTemp.getList();
        if(caseDOList.size() == 0){
            return PageResult.empty();
        }
        List<Integer> applicantIdList = new ArrayList<>();
        for(CaseDO caseDO : caseDOList){
            if(caseDO.getApplicantId() == null){
                continue;
            }
            applicantIdList.add(caseDO.getApplicantId());
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicantIdList", applicantIdList);
        List<SourceApplicantInfoDO> SourceApplicantInfoDOList = parkingPayUnionMapper.getSourceApplicantInfoByIds(paramMap);

        List<CaseRespVO> caseRespVOList = new ArrayList<>();
        for(CaseDO caseDO : caseDOList){
            CaseRespVO caseRespVO = new CaseRespVO();
            BeanUtil.copyProperties(caseDO, caseRespVO);
            if(caseDO.getApplicantId() != null){
                // 使用Stream API查找ID为3的Entity
                Optional<SourceApplicantInfoDO> foundEntity = SourceApplicantInfoDOList.stream()
                        .filter(entity -> entity.getId().equals(caseDO.getApplicantId()))
                        .findFirst();
                if(foundEntity.isPresent()){
                    caseRespVO.setApplicantName(foundEntity.get().getApplicant());
                }
            }
            caseRespVOList.add(caseRespVO);
        }

        return new PageResult<>(caseRespVOList, (long) caseRespVOList.size());
    }

    /**
     * 同步案件信息到仲裁系统
     *
     * @param id
     */
    @Override
    public void syncToArbi(Integer id) throws Exception{
        CaseDO caseDO = caseMapper.selectById(id);
        if(caseDO == null){
            throw new Exception("id错误， 没有此纪录");
        }

        SyncToArbiPotentialCaseVo reqVo = convertToSyncToArbiPotentialCaseVo(caseDO);

        String paramJson = "";
        try{
            paramJson = JSONUtil.toJsonStr(reqVo);
            System.out.println("同步待申请案件给仲裁系统， 参数： " + paramJson);
            log.info("同步待申请案件仲裁系统， 参数： {}", paramJson);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        String result = HttpUtil.post(syncPotentialCaseUrl, paramJson);
        log.info("同步待申请案件给仲裁系统， 返回： {}", result);
//        System.out.println("同步待申请案件给仲裁系统， 返回： " + result);

        ArbiSystemResponseVo responseVo = JSONUtil.toBean(result, ArbiSystemResponseVo.class);
        if(responseVo.getCode() != 0){
            throw new Exception(responseVo.getMsg());
        }

        caseDO.setSyncStatus(1);
        caseMapper.updateById(caseDO);
    }

    @Override
    public CaseRespVO getCaseInfo(Integer id) throws Exception{
        CaseDO caseDO  = getCase(id);
        Assert.notNull(caseDO);

        Integer applicantId = caseDO.getApplicantId();
        if(applicantId == null) {
            throw new Exception("没有找到申请人信息");
        }

        SourceApplicantInfoDO applicantInfoDO = applicantInfoMapper.selectById(applicantId);

        if(applicantInfoDO == null) {
            throw new Exception("没有找到申请人信息");
        }

        CaseRespVO caseRespVO = BeanUtils.toBean(caseDO, CaseRespVO.class);
        caseRespVO.setApplicantName(applicantInfoDO.getApplicant());
        return caseRespVO;
    }

    /**
     * 转换数据
     * @param caseDO
     * @return
     */
    private SyncToArbiPotentialCaseVo convertToSyncToArbiPotentialCaseVo(CaseDO caseDO) throws Exception{
        Integer applicantId = caseDO.getApplicantId();
        if(applicantId == null) {
            log.error("没有申请人id");
            throw new Exception("没有申请人id");
        }

        SourceApplicantInfoDO applicantInfoDO = applicantInfoMapper.selectById(applicantId);
        if(applicantInfoDO == null) {
            log.error("根据申请人id无法找到申请人");
            throw new Exception("根据申请人id无法找到申请人");
        }

        SyncToArbiPotentialCaseVo reqVo = new SyncToArbiPotentialCaseVo();
        reqVo.setCaseType(caseDO.getBusType());
//        reqVo.setMediationStatus(0);
//        reqVo.setIdentityStatus(0);
        reqVo.setRespondentType(caseDO.getRespondentType());
        reqVo.setRespondentName(caseDO.getRespondentName());
        reqVo.setIdentification(caseDO.getRespondentIdentification());
        reqVo.setPhone(caseDO.getRespondentPhone());
//        reqVo.setUuid(caseDO.)

        SpecificColumnVo specificColumnVo = new SpecificColumnVo();
        specificColumnVo.setCaseType(caseDO.getBusType());
        specificColumnVo.setSpecificValue(caseDO.getParam());

        reqVo.setSpecificColumn(specificColumnVo);
//        reqVo.setSpecificColumn(caseDO.getParam());
        reqVo.setClaimAmount(caseDO.getClaimAmount());
        reqVo.setAddress(caseDO.getRespondentAddress());
        reqVo.setSourceType(2);
        reqVo.setDocUrlTemp(caseDO.getDocUrls());
        reqVo.setUuid(caseDO.getUuid());
        reqVo.setStatus(0);
//        reqVo.setDocUrls(processDocUrlConvertion(caseDO.getDocUrls()));
        reqVo.setApplicantType(applicantInfoDO.getType());
        reqVo.setApplicantName(applicantInfoDO.getApplicant());
        reqVo.setApplicantIdInParkingPaySystem(applicantInfoDO.getId());
        reqVo.setApplicantIdentification(applicantInfoDO.getIdentification());

        return reqVo;
    }

    private List<DocInfoVo> processDocUrls(String docUrls){
        return null;
//        List<DocInfoVo> docInfoVoList = new ArrayList<>();
//
//        JSONObject jsonObject = JSONUtil.parseObj(docUrls);
//
//        jsonObject.forEach((key, value) -> {
//            JSONObject item = (JSONObject) value;
//            String fileUrl = item.getStr("fileUrl", null);
//            if (StringUtils.hasLength(fileUrl)) {
//                String title = item.getStr("title", null);
//                DocInfoVo docInfoVo = new DocInfoVo();
//                docInfoVo.setTitle(title);
//                docInfoVo.setTitle_en(key+"_list");
//                List<DocInfoMiniVo> fileList = new ArrayList<>();
//                DocInfoMiniVo docInfoMiniVo = new DocInfoMiniVo();
//                docInfoMiniVo.setTitle(title);
//                docInfoMiniVo.setTitle_en(key);
//            }
//        });


    }

}