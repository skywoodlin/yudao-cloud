package cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */
@Schema(description = "管理后台 - 申请方的信息表新增/修改 Request VO")
@Data
public class SourceApplicantInfoSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32744")
    private Integer id;

    @Schema(description = "source id", example = "24415")
    private Integer sourceId;

    @Schema(description = "作为申请方的公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "作为申请方的公司名称不能为空")
    private String applicant;

    @Schema(description = "作为申请方的公司信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "作为申请方的公司信息不能为空")
    private String applicantInfo;

    @Schema(description = "作为申请方的项目信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "作为申请方的项目信息不能为空")
    private String applicantProjectInfo;

    @Schema(description = "电子章URL", example = "https://www.iocoder.cn")
    private String signUrl;

    @Schema(description = "word模版Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28278")
//    @NotNull(message = "word模版Id不能为空")
    private Integer wordTemplateId;

    @Schema(description = "公众号名称")
    private String wechatPublic;

    @Schema(description = "公众号链接")
    private String wechatQrCode;

    @Schema(description = "行业类型： 1： 停车  2：其它待定", example = "1")
    private Integer busType;

    @Schema(description = "性别： 0: 女  1：男  null: 公司")
    private Integer sex;

    @Schema(description = "性质： 1： 个人  2： 公司", example = "2")
    private Integer type;

    @Schema(description = "生日")
    private LocalDateTime birthday;

    @Schema(description = "身份证号码或统一社会信用代码")
    private String identification;

//    @Schema(description = "身份证照片url", example = "https://www.iocoder.cn")
//    private String identificationImgUrl;

    @Schema(description = "法人姓名-公司才有", example = "李四")
    private String legalPersonName;

    @Schema(description = "法人身份证号-公司才有", example = "19534")
    private String legalPersonId;

//    @Schema(description = "法人身份证照片url-公司才有", example = "https://www.iocoder.cn")
//    private String legalPersonImgUrl;

    @Schema(description = "民族")
    private String ethnicity;

    @Schema(description = "个人地址或公司地址")
    private String address;

    @Schema(description = "个人电话或公司电话")
    private String phone;

//    @Schema(description = "营业执照图片或文档url", example = "https://www.iocoder.cn")
//    private String businessLicenseUrl;
//
//    @Schema(description = "法人代表身份证明文档url", example = "https://www.iocoder.cn")
//    private String legalPersonIdCertificateUrl;
//
//    @Schema(description = "授权委托书图片或文档url", example = "https://www.iocoder.cn")
//    private String authorizationLetterUrl;
//
//    @Schema(description = "所函图片或文档url", example = "https://www.iocoder.cn")
//    private String lawFirmLetterUrl;
//
//    @Schema(description = "代理律师律师证图片或文档url", example = "https://www.iocoder.cn")
//    private String lawyerLicenseUrl;

    @Schema(description = "仲裁需要的文件类型")
    private String arbiNeedFile;

    @Schema(description = "是否已同步到仲裁系统 0：否，  1：是")
    private Integer syncFlag;

    @Schema(description = "案件内容")
    private String caseContent;

    @Schema(description = "违约内容")
    private String illegalContent;

    @Schema(description = "待被申请方履行的义务")
    private String demand;
}
