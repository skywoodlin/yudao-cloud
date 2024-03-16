package cn.iocoder.yudao.module.system.service.parkingpayunion.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.cuser.vo.GetOwerecsByPlateNumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdReqVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdRespVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListProfitSharingInfoVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListWXProfitSharingVo;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.CuserPlateVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.EvidenceBarn;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.WxProfitSharing;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.WxProfitSharingInfo;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.DataSourcesMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.EvidenceBarnMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.OwerecMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ParkingPayUnionMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ProfitSharingInfoMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.WxProfitSharingMapper;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.module.system.util.encrypt.PuzekAESUtil.decrypt;

/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Service
@Slf4j
public class ParkingPayUnionServiceImpl implements ParkingPayUnionService{

    @Resource
    private ParkingPayUnionMapper parkingPayUnionMapper;

    @Resource
    private OwerecMapper owerecMapper;

    @Resource
    private DataSourcesMapper dataSourcesMapper;

    @Resource
    private ProfitSharingInfoMapper profitSharingInfoMapper;


    @Resource
    private WxProfitSharingMapper wxProfitSharingMapper;

    @Resource
    private EvidenceBarnMapper evidenceBarnMapper;

    @Resource
    private AdminUserService userService;



    @Override
    public PageResult<ListOwerecVo> listOwerec(ListOwerecReqVO reqVO) throws InvocationTargetException, IllegalAccessException{
        PageResult<Owerec> result_temp = owerecMapper.selectPage(reqVO);
        Long totalCounts = result_temp.getTotal();

        List<ListOwerecVo> result = new ArrayList<>();
        List<Owerec> owerecList = result_temp.getList();
        for(Owerec owerec : owerecList){
            ListOwerecVo listOwerecVo = new ListOwerecVo();
            BeanUtils.copyProperties(listOwerecVo, owerec);
            // 人名和手机号加密
            if(StringUtils.isNotEmpty(listOwerecVo.getName())){
                String realName = decrypt(listOwerecVo.getName());
                if(!reqVO.getDecryptAll()){
                    listOwerecVo.setName(doMaskForName(realName));
                }else{
                    listOwerecVo.setName(realName);
                }
            }
            if(StringUtils.isNotEmpty(listOwerecVo.getPhone())){
                String realPhone = decrypt(listOwerecVo.getPhone());
                if(!reqVO.getDecryptAll()){
                    listOwerecVo.setPhone(doMaskForPhone(realPhone));
                }else{
                    listOwerecVo.setPhone(realPhone);
                }
            }

            // todo 考虑缓存到redis中
            // 获取来源方名称
            String sourceName = parkingPayUnionMapper.getDataSourceNameById(listOwerecVo.getSourceId());
            listOwerecVo.setSourceName(sourceName);

            // 图片url
            List<String> photoUrlList = parkingPayUnionMapper.getPhotoListByOwerecId(listOwerecVo.getId());
            listOwerecVo.setPhotoUrls(photoUrlList);

            // 获取证据
            if(StringUtils.isNotEmpty(owerec.getThirdpartyOrderId())) {
                List<Integer> evidenceIdList =  parkingPayUnionMapper.getEviIdListByThirdpartyOrderId(owerec.getThirdpartyOrderId());
                listOwerecVo.setEvidenceIds(evidenceIdList);
            }

            result.add(listOwerecVo);
        }

        return new PageResult<>(result, totalCounts);
    }

    @Override
    public PageResult<ListOwerecVo> listOwerecForCUser(GetOwerecsByPlateNumReqVO reqVO) throws InvocationTargetException,
            IllegalAccessException{
        ListOwerecReqVO tempReqVo = new ListOwerecReqVO();
        tempReqVo.setPageNo(reqVO.getPageNo());
        tempReqVo.setPageSize(reqVO.getPageSize());
        tempReqVo.setPlateNum(reqVO.getPlateNum());
        tempReqVo.setPlateColor(reqVO.getPlateColor());
        PageResult<Owerec> result_temp = owerecMapper.selectPage(tempReqVo);

        List<ListOwerecVo> result = new ArrayList<>();
        List<Owerec> owerecList = result_temp.getList();

        Boolean isCurrentPlateNumVerified = userService.isCurrentPlateNumVerified(reqVO.getPlateNum(), reqVO.getPlateColor());

        for(Owerec owerec : owerecList){
            ListOwerecVo listOwerecVo = new ListOwerecVo();
            BeanUtils.copyProperties(listOwerecVo, owerec);
            // 人名和手机号加密
            if(StringUtils.isNotEmpty(listOwerecVo.getName())){
                String realName = decrypt(listOwerecVo.getName());
                    listOwerecVo.setName(doMaskForName(realName));
            }
            if(StringUtils.isNotEmpty(listOwerecVo.getPhone())){
                String realPhone = decrypt(listOwerecVo.getPhone());
                    listOwerecVo.setPhone(doMaskForPhone(realPhone));
            }

            // 图片url
            List<String> photoUrlList = parkingPayUnionMapper.getPhotoListByOwerecId(listOwerecVo.getId());
            listOwerecVo.setPhotoUrls(photoUrlList);

            // 获取证据
            if(StringUtils.isNotEmpty(owerec.getThirdpartyOrderId())) {
                List<Integer> evidenceIdList =  parkingPayUnionMapper.getEviIdListByThirdpartyOrderId(owerec.getThirdpartyOrderId());
                listOwerecVo.setEvidenceIds(evidenceIdList);
            }

            result.add(listOwerecVo);

            // 没验证的用户只看一条记录
            if(!isCurrentPlateNumVerified) {
                break;
            }
        }

        Long totalCounts = 0L;
        Long realCounts = result_temp.getTotal();
        if(isCurrentPlateNumVerified) {
            totalCounts = realCounts;
        }else{
            totalCounts = realCounts == 0L ? 0L:1L;
        }
        return new PageResult<>(result, totalCounts);
    }

    /**
     * 电话加星号
     *
     * @param phone
     * @return
     */
    private String doMaskForPhone(String phone){
        List<String> phoneList = Arrays.asList(phone.split(","));
        StringBuilder result_sb = new StringBuilder();
        for(String phoneStr: phoneList) {
            result_sb.append(maskMiddleForPhoneStr(phoneStr)).append(",");
        }
        result_sb.delete(result_sb.length()-1,result_sb.length());
        return result_sb.toString();
    }

    /**
     * 电话加星号
     *
     * @param phone
     * @return
     */
    private String maskMiddleForPhoneStr(String phone){
        if(phone.length() > 7){
            StringBuilder sb = new StringBuilder(phone.length());
            sb.append(phone.substring(0, 3));
            for(int i = 3; i < 7; i++){
                sb.append('*');
            }
            sb.append(phone.substring(7));
            return sb.toString();
        }else if(phone.length() <= 7 && phone.length() >= 4){
            StringBuilder sb = new StringBuilder(phone.length());
            sb.append(phone.substring(0, 3));
            for(int i = 3; i < phone.length(); i++){
                sb.append('*');
            }
            return sb.toString();
        }else{
            return phone;
        }
    }

    /**
     * 将名字加星号
     *
     * @param name
     * @return
     */
    private String doMaskForName(String name){
        if(name.length() > 2){
            StringBuilder sb = new StringBuilder(name.length());
            sb.append(name.charAt(0));
            for(int i = 1; i < name.length() - 1; i++){
                sb.append('*');
            }
            sb.append(name.charAt(name.length() - 1));
            return sb.toString();
        }else if(name.length() == 2){
            return name.charAt(0) + "*";
        }else{
            return name;
        }
    }

    @Override
    public Integer getSourceIdByAppid(Integer appid){
        DataSources dataSources = dataSourcesMapper.selectByAppid(appid);
        if(dataSources == null){
            return null;
        }

        return dataSources.getId();
    }

    @Override
    public PageResult<ListProfitSharingInfoVo> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO){
        Map<String, Object> paramMap = new HashMap<>();
        if(reqVO.getSourceId() != null){
            paramMap.put("sourceId", reqVO.getSourceId());
        }
        if(StringUtils.isNotEmpty(reqVO.getThirdpartyOrderId())){
            paramMap.put("thirdpartyOrderId", reqVO.getThirdpartyOrderId());
        }

        PageResult<ProfitSharingInfo> result_temp = profitSharingInfoMapper.selectPage(reqVO);
        Long totalCounts = result_temp.getTotal();
        List<ListProfitSharingInfoVo> result = new ArrayList<>();

        List<ProfitSharingInfo> profitSharingInfos = result_temp.getList();
        for(ProfitSharingInfo profitSharingInfo : profitSharingInfos){
            ListProfitSharingInfoVo listProfitSharingInfoVo = new ListProfitSharingInfoVo();
            try {
                BeanUtils.copyProperties(listProfitSharingInfoVo, profitSharingInfo);
            }catch(Exception e) {
                log.error(e.getMessage());
                continue;
            }

            // todo 考虑缓存到redis中
            // 获取来源方名称
            String sourceName = parkingPayUnionMapper.getDataSourceNameById(listProfitSharingInfoVo.getSourceId());
            listProfitSharingInfoVo.setSourceName(sourceName);
            result.add(listProfitSharingInfoVo);
        }

        return new PageResult<>(result, totalCounts);
    }


    @Override
    public PageResult<ListWXProfitSharingVo> getWXProfitSharingPage(GetWXProfitSharingReqVO reqVO){
        if(StringUtils.isEmpty(reqVO.getOutOrderNo())){
            reqVO.setOutOrderNo(null);
        }
        if(StringUtils.isEmpty(reqVO.getOrderCode())){
            reqVO.setOrderCode(null);
        }

        PageResult<WxProfitSharing> result_temp = wxProfitSharingMapper.selectPage(reqVO);
        Long totalCounts = result_temp.getTotal();
        List<ListWXProfitSharingVo> result = new ArrayList<>();

        List<WxProfitSharing> wxProfitSharings = result_temp.getList();
        for(WxProfitSharing wxProfitSharing : wxProfitSharings){
            ListWXProfitSharingVo listWXProfitSharingVo = new ListWXProfitSharingVo();
            try {
                BeanUtils.copyProperties(listWXProfitSharingVo, wxProfitSharing);
            }catch(Exception e) {
                log.error(e.getMessage());
                continue;
            }
            result.add(listWXProfitSharingVo);
        }

        return new PageResult<>(result, totalCounts);
    }

    @Override
    public List<DataSources> getDataSources(){
        return dataSourcesMapper.selectList();
    }

    /**
     * 获取分润汇总
     *
     * @param reqVO
     * @return
     */
    @Override
    public PageResult<GetProfitSharingInfoSumRespVO> getProfitSharingInfoSumPage(GetProfitSharingInfoSumReqVO reqVO){
        Map<String, Object> paramMap = new HashMap<>();
        Integer pageStart = (reqVO.getPageNo() - 1) * reqVO.getPageSize();
        paramMap.put("pageStart", pageStart);
        paramMap.put("pageSize", reqVO.getPageSize());
        if(reqVO.getChannelId() != null){
            paramMap.put("channelId", reqVO.getChannelId());
        }
        if(reqVO.getSourceId() != null){
            paramMap.put("sourceId", reqVO.getSourceId());
        }

        List<GetProfitSharingInfoSumRespVO> sharingInfoSumList = parkingPayUnionMapper.getProfitSharingInfoSum(paramMap);
        Long totalCounts = parkingPayUnionMapper.getProfitSharingInfoSumTotalCounts(paramMap);

        PageResult<GetProfitSharingInfoSumRespVO> result = new PageResult<>(sharingInfoSumList, totalCounts);

        return result;
    }

    @Override
    public PageResult<GetEvidenceBySourceIdRespVo> getEvidenceBySourceId(GetEvidenceBySourceIdReqVo reqVO){
        PageResult<EvidenceBarn> result_temp = evidenceBarnMapper.selectPage(reqVO);
        Long totalCounts = result_temp.getTotal();

        List<GetEvidenceBySourceIdRespVo> result = new ArrayList<>();
        List<EvidenceBarn> evidenceBarnList = result_temp.getList();


        String sourceName = "";
        if(evidenceBarnList.size() > 0){
            sourceName = parkingPayUnionMapper.getDataSourceNameById(evidenceBarnList.get(0).getSourceId());
        }

        for(EvidenceBarn evidenceBarn: evidenceBarnList) {
            GetEvidenceBySourceIdRespVo vo = new GetEvidenceBySourceIdRespVo();
            try {
                BeanUtils.copyProperties(vo, evidenceBarn);
            }catch(Exception e) {
                log.error(e.getMessage());
                continue;
            }
            vo.setSourceName(sourceName);
            result.add(vo);
        }

        return new PageResult<>(result, totalCounts);
    }

    /**
     * 更新CUser的认证状态
     * @param id
     * @param verifiedStatus
     */
    @Override
    public void updateVerifiedStatus(Integer id, Integer verifiedStatus, String verifyMsg){
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", id);
        paramMap.put("verifiedStatus", verifiedStatus);
        paramMap.put("verifyMsg", verifyMsg);
        parkingPayUnionMapper.updateVerifiedStatus(paramMap);
    }

    /**
     * 根据userId更新CUserPlate表信息
     * @param userId
     * @param plateNum
     * @param plateColor
     * @param imgUrl
     */
    @Override
    public void updateCUserPlate(Integer userId, String plateNum, String plateColor, String imgUrl){
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("userId", userId);
        paramMap.put("plateNum", plateNum);
        paramMap.put("plateColor", plateColor);
        paramMap.put("imgUrl", imgUrl);
        parkingPayUnionMapper.updateCUserPlateByUserId(paramMap);
    }

    @Override
    public List<WxProfitSharingInfo> getWXProfitSharingInfo(GetWXProfitSharingInfoReqVO reqVO){
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("orderCode", reqVO.getOrderCode());
        return parkingPayUnionMapper.getWXProfitSharingInfo(paramMap);
    }

    /**
     * 判断用户是否上传过此车牌
     * @return
     */
    private Boolean isCUserPlateExist(Map<String, Object> paramMap){
        CuserPlateVO cuserPlateVO = parkingPayUnionMapper.getCUserPlateByCondition(paramMap);

        return cuserPlateVO != null;
    }


}
