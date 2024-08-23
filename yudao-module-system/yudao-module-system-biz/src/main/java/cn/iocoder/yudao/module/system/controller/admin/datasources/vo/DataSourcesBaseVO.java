package cn.iocoder.yudao.module.system.controller.admin.datasources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import javax.validation.constraints.*;

/**
 * 欠费数据来源公司 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DataSourcesBaseVO {
    private Integer id;

    @Schema(description = "系统分配账户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "系统分配账户不能为空")
    private Integer appid;

    @Schema(description = "系统分配密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "系统分配密钥不能为空")
    private String secret;

    @Schema(description = "公司名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公司名称不能为空")
    private String name;

    @Schema(description = "使用渠道")
    private String channels;

    @Schema(description = "公司地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公司地址不能为空")
    private String address;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "支付结果回调地址(销账用)： 追缴中台定义回调内容，数据来源公司对接接口，数据来源公司提供回调地址")
    private String payResultUrl;

    @Schema(description = "分账编号")
    private Integer profitSharingId;

    @Schema(description = "状态： 0未启用  1启用  2弃用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态： 0未启用  1启用  2弃用不能为空")
    private Short status;

    @Schema(description = "每条短信费用")
    private BigDecimal smsFee;

    @Schema(description = "此数据来源使用的支付标识")
    private Integer payMode;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否删除  0： 否， 1：是")
//    @NotNull(message = "是否删除  0： 否， 1：是不能为空")
    private Short delFlag;

    @Schema(description = "微信商户号")
    private String mchId;

}
