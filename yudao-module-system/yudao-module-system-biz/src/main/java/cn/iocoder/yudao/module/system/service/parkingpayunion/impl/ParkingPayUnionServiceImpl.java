package cn.iocoder.yudao.module.system.service.parkingpayunion.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.DataSourcesMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.OwerecMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ParkingPayUnionMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ProfitSharingInfoMapper;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    ParkingPayUnionMapper parkingPayUnionMapper;

    @Resource
    OwerecMapper owerecMapper;

    @Resource
    DataSourcesMapper dataSourcesMapper;

    @Resource
    ProfitSharingInfoMapper profitSharingInfoMapper;


    @Override
    public PageResult<Owerec> listOwerec(ListOwerecReqVO reqVO){
        PageResult<Owerec> result = owerecMapper.selectPage(reqVO);
        // 人名和手机号加密
        List<Owerec> owerecList = result.getList();
        for(Owerec owerec : owerecList){
            if(StringUtils.isNotEmpty(owerec.getName())){
                String realName = decrypt(owerec.getName());
                owerec.setName(doMaskForName(realName));
            }

            if(StringUtils.isNotEmpty(owerec.getPhone())){
                String realPhone = decrypt(owerec.getPhone());
                owerec.setPhone(doMaskForPhone(realPhone));
            }
        }

        return result;
    }

    /**
     * 电话加星号
     *
     * @param phone
     * @return
     */
    private String doMaskForPhone(String phone){
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
    public PageResult<ProfitSharingInfo> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO){
        Map<String, Object> paramMap = new HashMap<>();
        if(reqVO.getSourceId() != null){
            paramMap.put("sourceId", reqVO.getSourceId());
        }
        if(StringUtils.isNotEmpty(reqVO.getThirdpartyOrderId())){
            paramMap.put("thirdpartyOrderId", reqVO.getThirdpartyOrderId());
        }

        return profitSharingInfoMapper.selectPage(reqVO);
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


}
