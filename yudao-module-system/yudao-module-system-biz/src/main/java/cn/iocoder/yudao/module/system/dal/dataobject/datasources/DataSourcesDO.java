package cn.iocoder.yudao.module.system.dal.dataobject.datasources;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 欠费数据来源公司 DO
 *
 * @author skywoodlin
 */
@TableName("t_data_sources")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSourcesDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 系统分配账户
     */
    private Integer appid;
    /**
     * 系统分配密钥
     */
    private String secret;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 使用渠道
     */
    private String channels;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 支付结果回调地址(销账用)： 追缴中台定义回调内容，数据来源公司对接接口，数据来源公司提供回调地址
     */
    private String payResultUrl;
    /**
     * 分账编号
     */
    private Integer profitSharingId;
    /**
     * 状态： 0未启用  1启用  2弃用
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Short status;
    /**
     * 此数据来源使用的支付标识
     */
    private Integer payMode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除  0： 否， 1：是
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Short delFlag;
    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 每条短信费用
     */
    private BigDecimal smsFee;

    /**
     * 微信商户号
     */
    private String mchId;

}
