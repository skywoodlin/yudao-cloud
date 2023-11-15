package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * Created by xiaojie.lin on 2023/7/5.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/7/5
 */
@Schema(description = "管理后台 - 追缴中台获取欠费信息 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ListOwerecReqVO extends PageParam{
    @Schema(description = "展示订单状态", example = "1")
    private Integer status;

    @Schema(description = "开始时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "来源方ID", example = "1")
    private Integer sourceId;

    @Schema(description = "第三方订单号", example = "11234")
    private String thirdpartyOrderId;

    @Schema(description = "车牌号", example = "闽D12345")
    private String plateNum;

    @Schema(description = "车牌颜色", example = "闽D12345")
    private String plateColor;

    @Schema(description = "追缴时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] retrieveTime;

    /**
     * 是否解密
     */
    private Boolean decryptAll;


}
