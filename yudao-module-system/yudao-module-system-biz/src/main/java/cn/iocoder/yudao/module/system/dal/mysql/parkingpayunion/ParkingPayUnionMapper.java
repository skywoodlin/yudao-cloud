package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Mapper
public interface ParkingPayUnionMapper {
    /**
     * 根据appid获取datasources
     */
    DataSources getDataSourceByAppid(Integer appid);


    List<GetProfitSharingInfoSumRespVO> getProfitSharingInfoSum(Map<String, Object> paramMap);


    Long getProfitSharingInfoSumTotalCounts(Map<String, Object> paramMap);


    /**
     * 接口： 从owerecId获取图片list
     * @param owerecId
     * @return
     */
    List<String> getPhotoListByOwerecId(Integer owerecId);


    String getDataSourceNameById(Integer dataSourceId);

}
