package cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 申请方的信息表 DO
 *
 * @author 仲裁管理员
 */
@TableName("t_source_applicant_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceApplicantInfoDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * source id
     */
    private Integer sourceId;
    /**
     * 作为申请方的公司名称
     */
    private String applicant;
    /**
     * 作为申请方的公司信息
     */
    private String applicantInfo;
    /**
     * 作为申请方的项目信息
     */
    private String applicantProjectInfo;
    /**
     * 电子章URL
     */
    private String signUrl;
    /**
     * word模版Id
     */
    private Integer wordTemplateId;
    /**
     * 公众号名称
     */
    private String wechatPublic;
    /**
     * 公众号链接
     */
    private String wechatQrCode;
    /**
     * 行业类型： 1： 停车  2：其它待定
     */
    private Integer busType;
    /**
     * 性别： 0: 女  1：男  null: 公司
     */
    private Integer sex;
    /**
     * 性质： 1： 个人  2： 公司
     */
    private Integer type;
    /**
     * 生日
     */
    private LocalDateTime birthday;
    /**
     * 身份证号码或统一社会信用代码
     */
    private String identification;
    /**
     * 身份证照片url
     */
    private String identificationImgUrl;
    /**
     * 法人姓名-公司才有
     */
    private String legalPersonName;
    /**
     * 法人身份证号-公司才有
     */
    private String legalPersonId;
    /**
     * 法人身份证照片url-公司才有
     */
    private String legalPersonImgUrl;
    /**
     * 民族
     */
    private String ethnicity;
    /**
     * 个人地址或公司地址
     */
    private String address;
    /**
     * 个人电话或公司电话
     */
    private String phone;
    /**
     * 营业执照图片或文档url
     */
    private String businessLicenseUrl;
    /**
     * 法人代表身份证明文档url
     */
    private String legalPersonIdCertificateUrl;
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
     * 仲裁需要的文件类型
     */
    private String arbiNeedFile;

    /**
     * 是否已同步到仲裁系统 0：否，  1：是
     */
    private Integer syncFlag;

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
