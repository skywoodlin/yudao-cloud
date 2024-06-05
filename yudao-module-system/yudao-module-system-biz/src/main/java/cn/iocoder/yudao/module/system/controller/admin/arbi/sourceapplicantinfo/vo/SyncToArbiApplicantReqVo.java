package cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo;

import lombok.Data;

/**
 * Created by xiaojie.lin on 2024/5/29.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/29
 */
@Data
public class SyncToArbiApplicantReqVo{
    /**
     * 追缴中台系统对应的id
     */
    private Integer parkingpaySystemId;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别, 公司留null
     */
    private Integer sex;
    /**
     * 性质： 1： 自然人，  2： 公司
     */
    private Integer type;
    /**
     * 身份证号码或统一社会信用代码
     */
    private String identification;
    /**
     * 身份证号码或统一社会信用代码图片或文档url
     */
    private String identificationUrl;
    /**
     * 营业执照图片或文档url
     */
    private String businessLicenseUrl;
    /**
     * 法人代表身份证图片或文档url
     */
    private String legalRepresentativeIdUrl;
    /**
     * 法人代表身份证明文档url
     */
    private String legalRepresentativeIdCertificateUrl;
    /**
     * 授权委托书图片或文档url
     */
    private String authorizationLetterUrl;
    /**
     * 所函图片或文档url
     */
    private String lawFirmLetterUrl;
    /**
     * 代理律师律师证图片或文档url
     */
    private String lawyerLicenseUrl;
    /**
     * 住址或通讯地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 案件内容
     */
    private String caseContent;
    /**
     * 违约内容
     */
    private String illegalContent;
    /**
     * 待被申请方履行的义务
     */
    private String demand;
}
