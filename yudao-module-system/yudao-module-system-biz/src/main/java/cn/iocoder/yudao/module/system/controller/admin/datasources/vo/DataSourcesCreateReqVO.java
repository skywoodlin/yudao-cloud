package cn.iocoder.yudao.module.system.controller.admin.datasources.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 欠费数据来源公司创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DataSourcesCreateReqVO extends DataSourcesBaseVO {

}
