package cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 仲裁业务类型新增/修改 Request VO")
@Data
public class BusinessTypeSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32744")
    private Integer id;

    @Schema(description = "业务名称", example = "张三")
    private String name;

    @Schema(description = "业务代码")
    private String code;

    @Schema(description = "仲裁所需要的文件类型")
    private String arbiNeedFile;

}