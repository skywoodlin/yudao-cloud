package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 欠费导入 Response VO")
@Data
@Builder
public class ImportOwerecRespVO{

//    @Schema(description = "导入成功的订单号数组", required = true)
//    private List<String> created;

//    @Schema(description = "导入失败的订单号数组", required = true)
//    private List<String> failedThirdpartyOrderId;

    @Schema(description = "导入失败的订单号集合，key 为三方订单号，value 为失败原因", required = true)
    private Map<String, String> failureReasons;

}