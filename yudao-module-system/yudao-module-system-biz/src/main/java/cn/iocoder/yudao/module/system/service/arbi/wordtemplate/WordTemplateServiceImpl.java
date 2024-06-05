package cn.iocoder.yudao.module.system.service.arbi.wordtemplate;

import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplatePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.wordtemplate.WordTemplateDO;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.sourceapplicantinfo.SourceApplicantInfoMapper;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.wordtemplate.WordTemplateMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ParkingPayUnionMapper;
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
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.WORD_TEMPLATE_NOT_EXISTS;

/**
 * 申请人模板配置 Service 实现类
 *
 * @author 仲裁管理员
 */
@Service
@Validated
public class WordTemplateServiceImpl implements WordTemplateService {

    @Resource
    private WordTemplateMapper wordTemplateMapper;

    @Resource
    private ParkingPayUnionMapper parkingPayUnionMapper;

    @Resource
    private SourceApplicantInfoMapper applicantInfoMapper;

    @Override
    public Integer createWordTemplate(WordTemplateSaveReqVO createReqVO) {
        // 插入
        WordTemplateDO wordTemplate = BeanUtils.toBean(createReqVO, WordTemplateDO.class);
        wordTemplateMapper.insert(wordTemplate);
        // 返回
        return wordTemplate.getId();
    }

    @Override
    public void updateWordTemplate(WordTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateWordTemplateExists(updateReqVO.getId());
        // 更新
        WordTemplateDO updateObj = BeanUtils.toBean(updateReqVO, WordTemplateDO.class);
        wordTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteWordTemplate(Integer id) {
        // 校验存在
        validateWordTemplateExists(id);
        // 删除
        wordTemplateMapper.deleteById(id);
    }

    private void validateWordTemplateExists(Integer id) {
        if (wordTemplateMapper.selectById(id) == null) {
            throw exception(WORD_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public WordTemplateDO getWordTemplate(Integer id) {
        return wordTemplateMapper.selectById(id);
    }

    @Override
//    public WordTemplateDO getWordTemplate(Integer id) {
    public WordTemplateRespVO getWordTemplateInfo(Integer id) throws Exception{
//        return wordTemplateMapper.selectById(id);
        WordTemplateDO wordTemplateDO = wordTemplateMapper.selectById(id);
        Assert.notNull(wordTemplateDO);
        Integer applicantId = wordTemplateDO.getApplicantInfoId();
        if(applicantId == null) {
            throw new Exception("没有找到申请人信息");
        }

        SourceApplicantInfoDO applicantInfoDO = applicantInfoMapper.selectById(applicantId);

        if(applicantInfoDO == null) {
            throw new Exception("没有找到申请人信息");
        }

        WordTemplateRespVO wordTemplateRespVO = BeanUtils.toBean(wordTemplateDO, WordTemplateRespVO.class);
        wordTemplateRespVO.setApplicantName(applicantInfoDO.getApplicant());
        return wordTemplateRespVO;
    }

    @Override
//    public PageResult<WordTemplateDO> getWordTemplatePage(WordTemplatePageReqVO pageReqVO) {
    public PageResult<WordTemplateRespVO> getWordTemplatePage(WordTemplatePageReqVO pageReqVO) {
//        return wordTemplateMapper.selectPage(pageReqVO);
        PageResult<WordTemplateDO> wordTemplateDOTemp = wordTemplateMapper.selectPage(pageReqVO);
        List<WordTemplateDO> wordTemplateDOList = wordTemplateDOTemp.getList();
        if(wordTemplateDOList.size() == 0){
            return PageResult.empty();
        }
        List<Integer> applicantIdList = new ArrayList<>();
        for(WordTemplateDO wordTemplateDO : wordTemplateDOList){
            if(wordTemplateDO.getApplicantInfoId() == null){
                continue;
            }
            applicantIdList.add(wordTemplateDO.getApplicantInfoId());
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicantIdList", applicantIdList);
        List<SourceApplicantInfoDO> SourceApplicantInfoDOList = parkingPayUnionMapper.getSourceApplicantInfoByIds(paramMap);


        List<WordTemplateRespVO> wordTemplateRespVOList = new ArrayList<>();

        for(WordTemplateDO wordTemplateDO : wordTemplateDOList){
            WordTemplateRespVO wordTemplateRespVO = new WordTemplateRespVO();
            BeanUtil.copyProperties(wordTemplateDO, wordTemplateRespVO);
            if(wordTemplateDO.getApplicantInfoId() != null){
                // 使用Stream API查找ID为3的Entity
                Optional<SourceApplicantInfoDO> foundEntity = SourceApplicantInfoDOList.stream()
                        .filter(entity -> entity.getId().equals(wordTemplateDO.getApplicantInfoId()))
                        .findFirst();
                if(foundEntity.isPresent()){
                    wordTemplateRespVO.setApplicantName(foundEntity.get().getApplicant());
                }
            }
            wordTemplateRespVOList.add(wordTemplateRespVO);
        }
        return new PageResult<>(wordTemplateRespVOList, (long) wordTemplateRespVOList.size());
    }
}