package cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo;

import cn.iocoder.yudao.module.system.service.arbi.cases.vo.DocInfoVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xiaojie.lin on 2024/5/31.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/31
 */
@Data
public class SyncToArbiPotentialCaseVo{
    /**
     * 案件类型： 1：停车欠费 2：待定
     */
    private Integer caseType;
//    /**
//     * 调解状态： 0： 待调解， 1：调解失败  2：调解成功
//     */
//    private Integer mediationStatus;
//    /**
//     * 资料状态：0：资料不完整  1： 资料完整
//     */
//    private Integer identityStatus;
    /**
     * 性质： 1： 自然人，  2： 公司
     */
    private Integer respondentType;
    /**
     * 被申请人名称
     */
    private String respondentName;
//    /**
//     * 被申请人ID，参照parties表， 字段有值代表个人资料完整， 可以转案件受理
//     */
//    private Integer respondentId;
    /**
     * 身份证号码或统一社会信用代码
     */
    private String identification;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 车牌号
     */
    private String plateNum;
    /**
     * 车牌颜色
     */
    private String plateColor;
    /**
     * 诉求金额
     */
    private BigDecimal claimAmount;
    /**
     * 住址或通讯地址
     */
    private String address;

    /**
     * 来源： 1. 追缴中台发送短信后同步   2. 追缴中台同步-未发短信
     */
    private Integer sourceType;

    /**
     * 唯一标识
     */
    private String uuid;

//    /**
//     * 仲裁申请书pdf url
//     */
//    private String applicationPdfUrl;
//
//    /**
//     * 已发短信次数
//     */
//    private Integer smsSendCounts;

//    /**
//     * 欠费最早时间
//     */
//    private LocalDateTime firstOweTime;
//
//    /**
//     * 欠费最晚时间
//     */
//    private LocalDateTime lastOweTime;

//    /**
//     * 欠费单数
//     */
//    private Integer oweCounts;

    /**
     * 状态：
     * 0： 待申请
     * 1： 已申请
     * 2： 已审批
     * 3： 调解中
     * 4： 调解成功
     * 5： 调解失败（可能不要）
     * 6： 立案申请
     * 7.  立案申请已审批
     * 9： 已撤销
     */
    private Integer status;

//    /**
//     * 来源区域
//     */
//    private String sourceArea;

    /**
     * 各文档url
     */
    private String docUrlTemp;
//    private List<DocInfoVo> docUrls;

    /**
     * 不同案件类型特有的列
     */
    private SpecificColumnVo specificColumn;

    /**
     * 申请人性质： 1： 自然人， 2： 公司
     */
    private Integer applicantType;
    /**
     * 申请人名称
     */
    private String applicantName;
    /**
     * 申请人ID，参照t_applicant_info表
     */
//    private Integer applicantId;
    /**
     * 申请人身份证号码或统一社会信用代码
     */
    private String applicantIdentification;

    private Integer applicantIdInParkingPaySystem;

//    private Integer caseIdInParkingPaySystem;
}
