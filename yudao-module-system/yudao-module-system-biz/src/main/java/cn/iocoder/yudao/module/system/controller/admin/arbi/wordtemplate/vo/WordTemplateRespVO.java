package cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 申请人模板配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WordTemplateRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10254")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "模版填充内容")
    @ExcelProperty("模版填充内容")
    private String content;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "文件标题")
    @ExcelProperty("文件标题")
    private String title;

    @Schema(description = "模版名称", example = "王五")
    @ExcelProperty("模版名称")
    private String templateName;

    @Schema(description = "申请人表ID", example = "31110")
    @ExcelProperty("申请人表ID")
    private Integer applicantInfoId;

    @Schema(description = "申请人名字", example = "21762")
    @ExcelProperty("申请人名字")
    private String applicantName;

}