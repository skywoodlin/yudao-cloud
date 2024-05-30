package cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */
@Schema(description = "管理后台 - 申请方的信息表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SourceApplicantInfoRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29879")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "source id", example = "24415")
    @ExcelProperty("source id")
    private Integer sourceId;

    @Schema(description = "作为申请方的公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("作为申请方的公司名称")
    private String applicant;

    @Schema(description = "作为申请方的公司信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("作为申请方的公司信息")
    private String applicantInfo;

    @Schema(description = "作为申请方的项目信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("作为申请方的项目信息")
    private String applicantProjectInfo;

    /**
     * 电子章URL
     */
    @Schema(description = "电子章URL")
    @ExcelProperty("电子章URL")
    private String signUrl;


    @Schema(description = "word模版Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28278")
    @ExcelProperty("word模版Id")
    private Integer wordTemplateId;

    /**
     * 公众号名称
     */
    @Schema(description = "公众号名称")
    @ExcelProperty("公众号名称")
    private String wechatPublic;
    /**
     * 公众号链接
     */
    @Schema(description = "公众号链接")
    @ExcelProperty("公众号链接")
    private String wechatQrCode;

    @Schema(description = "行业类型： 1： 停车  2：其它待定", example = "1")
    @ExcelProperty("行业类型： 1： 停车  2：其它待定")
    private Integer busType;

    @Schema(description = "性别： 0: 女  1：男  null: 公司")
    @ExcelProperty("性别： 0: 女  1：男  null: 公司")
    private Integer sex;

    @Schema(description = "性质： 1： 个人  2： 公司", example = "2")
    @ExcelProperty("性质： 1： 个人  2： 公司")
    private Integer type;

    @Schema(description = "生日")
    @ExcelProperty("生日")
    private LocalDateTime birthday;

    @Schema(description = "身份证号码或统一社会信用代码")
    @ExcelProperty("身份证号码或统一社会信用代码")
    private String identification;

    @Schema(description = "身份证照片url")
    @ExcelProperty("身份证照片url")
    private String identificationImgUrl;

    @Schema(description = "法人姓名-公司才有", example = "李四")
    @ExcelProperty("法人姓名-公司才有")
    private String legalPersonName;

    @Schema(description = "法人身份证号-公司才有", example = "19534")
    @ExcelProperty("法人身份证号-公司才有")
    private String legalPersonId;

    @Schema(description = "法人身份证照片url")
    @ExcelProperty("法人身份证照片url")
    private String legalPersonImgUrl;

    /**
     * 民族
     */
    @Schema(description = "民族")
    @ExcelProperty("民族")
    private String ethnicity;

    @Schema(description = "个人地址或公司地址")
    @ExcelProperty("个人地址或公司地址")
    private String address;

    @Schema(description = "个人电话或公司电话")
    @ExcelProperty("个人电话或公司电话")
    private String phone;

    /**
     * 营业执照图片或文档url
     */
    @Schema(description = "营业执照图片或文档url")
    @ExcelProperty("营业执照图片或文档url")
    private String businessLicenseUrl;
    /**
     * 法人代表身份证明文档url
     */
    @Schema(description = "法人代表身份证明文档url")
    @ExcelProperty("法人代表身份证明文档url")
    private String legalPersonIdCertificateUrl;
    /**
     * 授权委托书图片或文档url
     */
    @Schema(description = "授权委托书图片或文档url")
    @ExcelProperty("授权委托书图片或文档url")
    private String authorizationLetterUrl;
    /**
     * 所函图片或文档url
     */
    @Schema(description = "所函图片或文档url")
    @ExcelProperty("所函图片或文档url")
    private String lawFirmLetterUrl;
    /**
     * 代理律师律师证图片或文档url
     */
    @Schema(description = "代理律师律师证图片或文档url")
    @ExcelProperty("代理律师律师证图片或文档url")
    private String lawyerLicenseUrl;

    @Schema(description = "仲裁所需要的文件类型")
    @ExcelProperty("仲裁所需要的文件类型")
    private String arbiNeedFile;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "是否已同步到仲裁系统 0：否，  1：是")
    @ExcelProperty("是否已同步到仲裁系统")
    private Integer syncFlag;

}