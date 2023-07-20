package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingInfo;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Tag(name =  "管理后台 - 追缴中台")
@RestController
@RequestMapping("/system/parkingpayunion")
@Validated
@Slf4j
public class ParkingPayUnionController{

    @Resource
    private ParkingPayUnionService parkingPayUnionService;


    @GetMapping("/listOwerecPage")
    @Operation(summary = "获取欠费信息list Page")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<Owerec>> listOwerecPage(ListOwerecReqVO reqVO) {
        return success(parkingPayUnionService.listOwerec(reqVO));
    }


    @GetMapping("/getProfitSharingInfoPage")
    @Operation(summary = "获取欠费信息list Page")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<ProfitSharingInfo>> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO) {
        return success(parkingPayUnionService.getProfitSharingInfoPage(reqVO));
    }

    @GetMapping("/getDataSources")
    @Operation(summary = "获取所有来源方信息", description = "主要用于前端的下拉选项")
    public CommonResult<List<DataSources>> getDataSources() {
        // 获得岗位列表，只要开启状态的
        List<DataSources> list = parkingPayUnionService.getDataSources();
        // 排序后，返回给前端
        list.sort(Comparator.comparing(DataSources::getName));
        return success(list);
    }

    @GetMapping("/getProfitSharingInfoSumPage")
    @Operation(summary = "获取分润汇总信息")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<GetProfitSharingInfoSumRespVO>> getProfitSharingInfoSumPage(GetProfitSharingInfoSumReqVO reqVO) {
        return success(parkingPayUnionService.getProfitSharingInfoSumPage(reqVO));
    }



}
