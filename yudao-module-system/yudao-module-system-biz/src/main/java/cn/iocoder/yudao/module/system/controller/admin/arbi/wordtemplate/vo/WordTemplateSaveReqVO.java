package cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 申请人模板配置新增/修改 Request VO")
@Data
public class WordTemplateSaveReqVO {
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32744")
    private Integer id;

    @Schema(description = "来源ID", example = "20383")
    private Integer sourcesId;

    @Schema(description = "模版填充内容")
    private String content;

    @Schema(description = "文件标题")
    private String title;

    @Schema(description = "模版名称", example = "王五")
    private String templateName;

    @Schema(description = "申请人表ID", example = "31110")
    private Integer applicantInfoId;

}