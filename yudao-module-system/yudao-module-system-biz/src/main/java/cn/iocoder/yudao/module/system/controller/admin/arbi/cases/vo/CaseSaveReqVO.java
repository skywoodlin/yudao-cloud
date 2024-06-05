package cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 案件新增/修改 Request VO")
@Data
public class CaseSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19289")
    private Integer id;

    @Schema(description = "同步状态， 0： 未同步 1：已同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "同步状态， 0： 未同步 1：已同步不能为空")
    private Integer syncStatus;

    @Schema(description = "行业类型： 1： 停车  2： 其它待定", example = "2")
    private Integer busType;

    @Schema(description = "申请人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21762")
    @NotNull(message = "申请人id不能为空")
    private Integer applicantId;

    @Schema(description = "被申请人名字", example = "王五")
    private String respondentName;

    @Schema(description = "被申请人性别： 0: 女  1: 男 null: 公司")
    private Integer respondentSex;

    @Schema(description = "被申请人性质： 1： 个人  2： 公司", example = "2")
    private Integer respondentType;

    @Schema(description = "被申请人出生日期")
    private String respondentBirthday;

    @Schema(description = "被申请人身份证号码或统一社会信用代码")
    private String respondentIdentification;

    @Schema(description = "被申请人电话")
    private String respondentPhone;

    @Schema(description = "被申请人地址")
    private String respondentAddress;

    @Schema(description = "申请金额")
    private BigDecimal claimAmount;

    @Schema(description = "其它参数")
    private String param;

    @Schema(description = "各种文档url")
    private String docUrls;

}