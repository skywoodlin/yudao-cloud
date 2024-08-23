package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */

@Schema(description = "管理后台 - 渠道新增/修改 Request VO")
@Data
public class ChannelSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32450")
    private Integer id;

    @Schema(description = "本地appid", requiredMode = Schema.RequiredMode.REQUIRED, example = "28150")
    @NotEmpty(message = "本地appid不能为空")
    private String appid;

    @Schema(description = "本地secret", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "本地secret不能为空")
    private String secret;

    @Schema(description = "渠道名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "渠道名称不能为空")
    private String channelName;

    @Schema(description = "渠道类型: 1 平台类 2 即时类 3 支付通道类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "渠道类型: 1 平台类 2 即时类 3 支付通道类不能为空")
    private Integer channelType;

    @Schema(description = "状态： 1.启用 2.停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态： 1.启用 2.停用不能为空")
    private Short status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "修改订单url", example = "https://www.iocoder.cn")
    private String modifyUrl;

    @Schema(description = "通知订单更新url:订单支付后，通知其他渠道更新订单状态", example = "https://www.iocoder.cn")
    private String informUrl;

    @Schema(description = "微信商户号", example = "25329")
    private String mchId;

    @Schema(description = "渠道方appid")
    private String appidRemote;

    @Schema(description = "渠道方secret")
    private String secretRemote;

    @Schema(description = "通知渠道方的bean名字", example = "张三")
    private String notifyBeanName;

    @Schema(description = "可供拉取数据的来源方id(逗号分隔)", example = "12388")
    private String pushSourceId;

    @Schema(description = "推送欠费给渠道方的url", example = "https://www.iocoder.cn")
    private String pushOwerecUrl;

    @Schema(description = "修改欠费后同步推送修改给渠道方的url", example = "https://www.iocoder.cn")
    private String modifyOwerecUrl;

    @Schema(description = "删除欠费后同步删除记录给渠道方的url", example = "https://www.iocoder.cn")
    private String deleteOwerecUrl;

    @Schema(description = "本地缴费成功后通知渠道方不要再缴费的url", example = "https://www.iocoder.cn")
    private String notifyUrl;

}
