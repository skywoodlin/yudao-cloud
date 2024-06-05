package cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Schema(description = "管理后台 - 案件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CasePageReqVO extends PageParam{

    @Schema(description = "同步状态， 0： 未同步 1：已同步", example = "1")
    private Integer syncStatus;

    @Schema(description = "行业类型： 1： 停车  2： 其它待定", example = "2")
    private Integer busType;

    @Schema(description = "被申请人名字", example = "王五")
    private String respondentName;

    @Schema(description = "被申请人电话")
    private String respondentPhone;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "uuid")
    private String uuid;

}