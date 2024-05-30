package cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Schema(description = "管理后台 - 仲裁业务类型分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BusinessTypePageReqVO extends PageParam{

    @Schema(description = "业务名称", example = "张三")
    private String name;

    @Schema(description = "业务代码")
    private String code;

    @Schema(description = "仲裁所需要的文件类型")
    private String arbiNeedFile;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
