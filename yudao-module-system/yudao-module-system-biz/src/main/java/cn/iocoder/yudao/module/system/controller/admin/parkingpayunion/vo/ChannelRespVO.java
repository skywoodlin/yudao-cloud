package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */
@Schema(description = "管理后台 - 渠道 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChannelRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23028")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "本地appid", requiredMode = Schema.RequiredMode.REQUIRED, example = "28150")
    @ExcelProperty("本地appid")
    private String appid;

    @Schema(description = "本地secret", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("本地secret")
    private String secret;

    @Schema(description = "渠道名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("渠道名称")
    private String channelName;

    @Schema(description = "渠道类型: 1 平台类 2 即时类 3 支付通道类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("渠道类型: 1 平台类 2 即时类 3 支付通道类")
    private Integer channelType;

    @Schema(description = "状态： 1.启用 2.停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态： 1.启用 2.停用")
    private Short status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "修改订单url", example = "https://www.iocoder.cn")
    @ExcelProperty("修改订单url")
    private String modifyUrl;

    @Schema(description = "通知订单更新url:订单支付后，通知其他渠道更新订单状态", example = "https://www.iocoder.cn")
    @ExcelProperty("通知订单更新url:订单支付后，通知其他渠道更新订单状态")
    private String informUrl;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "微信商户号", example = "25329")
    @ExcelProperty("微信商户号")
    private String mchId;

    @Schema(description = "渠道方appid")
    @ExcelProperty("渠道方appid")
    private String appidRemote;

    @Schema(description = "渠道方secret")
    @ExcelProperty("渠道方secret")
    private String secretRemote;

    @Schema(description = "通知渠道方的bean名字", example = "张三")
    @ExcelProperty("通知渠道方的bean名字")
    private String notifyBeanName;

    @Schema(description = "可供拉取数据的来源方id(逗号分隔)", example = "12388")
    @ExcelProperty("可供拉取数据的来源方id(逗号分隔)")
    private String pushSourceId;

    @Schema(description = "推送欠费给渠道方的url", example = "https://www.iocoder.cn")
    @ExcelProperty("推送欠费给渠道方的url")
    private String pushOwerecUrl;

    @Schema(description = "修改欠费后同步推送修改给渠道方的url", example = "https://www.iocoder.cn")
    @ExcelProperty("修改欠费后同步推送修改给渠道方的url")
    private String modifyOwerecUrl;

    @Schema(description = "删除欠费后同步删除记录给渠道方的url", example = "https://www.iocoder.cn")
    @ExcelProperty("删除欠费后同步删除记录给渠道方的url")
    private String deleteOwerecUrl;

    @Schema(description = "本地缴费成功后通知渠道方不要再缴费的url", example = "https://www.iocoder.cn")
    @ExcelProperty("本地缴费成功后通知渠道方不要再缴费的url")
    private String notifyUrl;

}
