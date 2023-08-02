package cn.iocoder.yudao.module.system.service.parkingpayunion.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListProfitSharingInfoVo;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.DataSourcesMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.OwerecMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ParkingPayUnionMapper;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ProfitSharingInfoMapper;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
                listOwerecVo.setName(doMaskForName(realName));
            }
            if(StringUtils.isNotEmpty(listOwerecVo.getPhone())){
                String realPhone = decrypt(listOwerecVo.getPhone());
                listOwerecVo.setPhone(doMaskForPhone(realPhone));
            }

            // todo 考虑缓存到redis中
            // 获取来源方名称
            String sourceName = parkingPayUnionMapper.getDataSourceNameById(listOwerecVo.getSourceId());
            listOwerecVo.setSourceName(sourceName);

            // 图片url
            List<String> photoUrlList = parkingPayUnionMapper.getPhotoListByOwerecId(listOwerecVo.getId());
            listOwerecVo.setPhotoUrls(photoUrlList);
            result.add(listOwerecVo);
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
