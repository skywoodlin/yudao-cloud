package cn.iocoder.yudao.module.system.controller.admin.datasources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Schema(description = "管理后台 - 欠费数据来源公司 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DataSourcesRespVO extends DataSourcesBaseVO {

    @Schema(description = "删除时间")
    private Date deleteTime;

    @Schema(description = "创建时间")
    private Date createTime;

}
