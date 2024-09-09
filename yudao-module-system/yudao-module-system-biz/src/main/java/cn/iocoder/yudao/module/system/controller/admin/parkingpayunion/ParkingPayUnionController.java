package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdReqVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetEvidenceBySourceIdRespVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetProfitSharingInfoSumRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingInfoReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.GetWXProfitSharingReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ImportOwerecVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListProfitSharingInfoVo;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListWXProfitSharingVo;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.WxProfitSharingInfo;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
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
    public CommonResult<PageResult<ListOwerecVo>> listOwerecPage(ListOwerecReqVO reqVO) throws InvocationTargetException, IllegalAccessException{
        return success(parkingPayUnionService.listOwerec(reqVO));
    }


    @GetMapping("/getProfitSharingInfoPage")
    @Operation(summary = "获取分润明细list Page")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<ListProfitSharingInfoVo>> getProfitSharingInfoPage(GetProfitSharingInfoReqVO reqVO) {
        return success(parkingPayUnionService.getProfitSharingInfoPage(reqVO));
    }

    @GetMapping("/getWXSplitShare")
    @Operation(summary = "获取微信分账list Page")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<ListWXProfitSharingVo>> getProfitSharingInfoPage(GetWXProfitSharingReqVO reqVO) {
        return success(parkingPayUnionService.getWXProfitSharingPage(reqVO));
    }

    @GetMapping("/getWxProfitSharingInfo")
    @Operation(summary = "获取微信分账明细list")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<List<WxProfitSharingInfo>> getWxProfitSharingInfo(GetWXProfitSharingInfoReqVO reqVO) {
        return success(parkingPayUnionService.getWXProfitSharingInfo(reqVO));    }

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

    @PostMapping("/importOwerec")
    @Operation(summary = "导入用户")
//    @PreAuthorize("@ss.hasPermission('system:user:import')")
//    public CommonResult<ImportOwerecRespVO> importOwerecExcel(@RequestParam("file") MultipartFile file,
    public void importOwerecExcel(@RequestParam("file") MultipartFile file,
                                                              @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<ImportOwerecVo> list = ExcelUtils.read(file, ImportOwerecVo.class);
        System.out.println("hh");
//        return success(parkingPayUnionService.importOwerec(list, updateSupport));
    }


    @GetMapping("/getEvidenceBySourceId")
    @Operation(summary = "获取用户证据")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<PageResult<GetEvidenceBySourceIdRespVo>> getEvidenceBySourceId(GetEvidenceBySourceIdReqVo reqVO) {
        return success(parkingPayUnionService.getEvidenceBySourceId(reqVO));
    }

    @PostMapping("/owerecRefund")
    @Operation(summary = "退款接口")
    // todo 这里本来要设置权限， 先不设
    @PermitAll
    public CommonResult<String> owerecRefund(Integer owerecId) {
        if(owerecId == null) {
            return error(400, "owerecId不能为空"); // 参数不正确
        }
        String result = null;
        try{
            result = parkingPayUnionService.owerecRefund(owerecId);
        }catch(Exception e) {
            return error(500, e.getMessage()); //
        }
        if(StringUtils.hasLength(result)) {
            return error(500, result);
        }
        return success("success");
    }


}
