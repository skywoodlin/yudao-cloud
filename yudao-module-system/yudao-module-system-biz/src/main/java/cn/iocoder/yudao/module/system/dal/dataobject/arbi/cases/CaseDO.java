package cn.iocoder.yudao.module.system.dal.dataobject.arbi.cases;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 案件 DO
 *
 * @author 仲裁管理员
 */
@TableName("t_case")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 同步状态， 0： 未同步 1：已同步
     */
    private Integer syncStatus;
    /**
     * 行业类型： 1： 停车  2： 其它待定
     */
    private Integer busType;
    /**
     * 申请人id
     */
    private Integer applicantId;
    /**
     * 被申请人名字
     */
    private String respondentName;
    /**
     * 被申请人性别： 0: 女  1: 男 null: 公司
     */
    private Integer respondentSex;
    /**
     * 被申请人性质： 1： 个人  2： 公司
     */
    private Integer respondentType;
    /**
     * 被申请人出生日期
     */
    private String respondentBirthday;
    /**
     * 被申请人身份证号码或统一社会信用代码
     */
    private String respondentIdentification;
    /**
     * 被申请人电话
     */
    private String respondentPhone;
    /**
     * 被申请人地址
     */
    private String respondentAddress;
    /**
     * 申请金额
     */
    private BigDecimal claimAmount;
    /**
     * 短信发送次数， 不一定要， 可以实时统计
     */
    private Integer smsSendCount;
    /**
     * 其它参数
     */
    private String param;
    /**
     * 各种文档url
     */
    private String docUrls;

    /**
     * uuid
     */
    private String uuid;

}