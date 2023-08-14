package cn.iocoder.yudao.module.system.controller.admin.datasources.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 欠费数据来源公司分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DataSourcesPageReqVO extends PageParam {

    @Schema(description = "系统分配账户")
    private Integer appid;

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "分账编号")
    private Integer profitSharingId;

    @Schema(description = "状态： 0未启用  1启用  2弃用")
    private Short status;

    @Schema(description = "是否删除  0： 否， 1：是")
    private Short delFlag;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
