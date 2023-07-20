package cn.iocoder.yudao.module.system.service.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;

import java.util.List;

/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
public interface ParkingPayUnionService{

    PageResult<Owerec> listOwerec(ListOwerecReqVO reqVO);


    Integer getSourceIdByAppid(Integer appid);

    PageResult<ProfitSharingInfo> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO);

    List<DataSources> getDataSources();

    PageResult<GetProfitSharingInfoSumRespVO> getProfitSharingInfoSumPage(GetProfitSharingInfoSumReqVO reqVO);
}
