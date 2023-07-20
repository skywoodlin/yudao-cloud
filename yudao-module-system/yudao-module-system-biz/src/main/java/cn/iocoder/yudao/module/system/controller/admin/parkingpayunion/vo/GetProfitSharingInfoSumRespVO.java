package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ProfitSharingSum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Schema(description = "管理后台 - 追缴中台分润汇总 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class GetProfitSharingInfoSumRespVO extends ProfitSharingSum{

    @Schema(description = "渠道方ID", example = "1")
    private String channelName;

    @Schema(description = "来源方ID", example = "1")
    private String sourceName;
}
