package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Schema(description = "管理后台 - 追缴中台分润汇总 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class GetProfitSharingInfoSumReqVO extends PageParam{

    @Schema(description = "渠道方ID", example = "1")
    private Integer channelId;

    @Schema(description = "来源方ID", example = "1")
    private Integer sourceId;
}
