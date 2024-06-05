package cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 案件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CaseRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19289")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "同步状态， 0： 未同步 1：已同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("同步状态， 0： 未同步 1：已同步")
    private Integer syncStatus;

    @Schema(description = "行业类型： 1： 停车  2： 其它待定", example = "2")
    @ExcelProperty("行业类型： 1： 停车  2： 其它待定")
    private Integer busType;

    @Schema(description = "申请人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21762")
    @ExcelProperty("申请人id")
    private Integer applicantId;

    @Schema(description = "申请人名字", example = "21762")
    @ExcelProperty("申请人名字")
    private String applicantName;

    @Schema(description = "被申请人名字", example = "王五")
    @ExcelProperty("被申请人名字")
    private String respondentName;

    @Schema(description = "被申请人性别： 0: 女  1: 男 null: 公司")
    @ExcelProperty("被申请人性别： 0: 女  1: 男 null: 公司")
    private Integer respondentSex;

    @Schema(description = "被申请人性质： 1： 个人  2： 公司", example = "2")
    @ExcelProperty("被申请人性质： 1： 个人  2： 公司")
    private Integer respondentType;

    @Schema(description = "被申请人出生日期")
    @ExcelProperty("被申请人出生日期")
    private String respondentBirthday;

    @Schema(description = "被申请人身份证号码或统一社会信用代码")
    @ExcelProperty("被申请人身份证号码或统一社会信用代码")
    private String respondentIdentification;

    @Schema(description = "被申请人电话")
    @ExcelProperty("被申请人电话")
    private String respondentPhone;

    @Schema(description = "被申请人地址")
    @ExcelProperty("被申请人地址")
    private String respondentAddress;

    @Schema(description = "申请金额")
    @ExcelProperty("申请金额")
    private BigDecimal claimAmount;

    @Schema(description = "短信发送次数， 不一定要， 可以实时统计", example = "17528")
    @ExcelProperty("短信发送次数， 不一定要， 可以实时统计")
    private Integer smsSendCount;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "其它参数")
    @ExcelProperty("其它参数")
    private String param;

    @Schema(description = "各种文档url")
    @ExcelProperty("各种文档url")
    private String docUrls;

    @Schema(description = "uuid")
    @ExcelProperty("uuid")
    private String uuid;

}