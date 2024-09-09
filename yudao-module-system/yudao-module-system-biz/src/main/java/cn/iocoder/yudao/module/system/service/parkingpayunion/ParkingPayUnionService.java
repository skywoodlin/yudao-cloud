package cn.iocoder.yudao.module.system.service.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.cuser.vo.GetOwerecsByPlateNumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdReqVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdRespVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecForChannelReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListProfitSharingInfoVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListWXProfitSharingVo;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.WxProfitSharingInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
public interface ParkingPayUnionService{

    PageResult<ListOwerecVo> listOwerec(ListOwerecReqVO reqVO) throws InvocationTargetException, IllegalAccessException;

    PageResult<ListOwerecVo> listOwerecForCUser(GetOwerecsByPlateNumReqVO reqVO) throws InvocationTargetException, IllegalAccessException;


    Integer getSourceIdByAppid(Integer appid);

    PageResult<ListProfitSharingInfoVo> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO);

    PageResult<ListWXProfitSharingVo> getWXProfitSharingPage(GetWXProfitSharingReqVO reqVO);

    List<DataSources> getDataSources();

    PageResult<GetProfitSharingInfoSumRespVO> getProfitSharingInfoSumPage(GetProfitSharingInfoSumReqVO reqVO);

    PageResult<GetEvidenceBySourceIdRespVo> getEvidenceBySourceId(GetEvidenceBySourceIdReqVo reqVO);

    /**
     * 更新CUser的认证状态
     * @param id
     * @param verifiedStatus
     */
    void updateVerifiedStatus(Integer id, Integer verifiedStatus, String verifyMsg);

    /**
     * 根据userId更新CUserPlate表信息
     * @param userId
     * @param plateNum
     * @param plateColor
     * @param imgUrl
     */
    void updateCUserPlate(Integer userId, String plateNum, String plateColor, String imgUrl);

    /**
     * 微信分账明细
     * @param reqVO
     * @return
     */
    List<WxProfitSharingInfo> getWXProfitSharingInfo(GetWXProfitSharingInfoReqVO reqVO);

    /**
     * 欠费订单退款 - 根据欠费订单id退款
     *
     * @param owerecId
     * @return 退款结果， 如果返回值为空， 说明退款成功， 否则返回错误信息
     */
    String owerecRefund(Integer owerecId);
}
