package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 渠道 DO
 *
 * @author 诉前管理员
 */
@TableName("t_channel")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 本地appid
     */
    private String appid;
    /**
     * 本地secret
     */
    private String secret;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道类型: 1 平台类 2 即时类 3 支付通道类
     */
    private Integer channelType;
    /**
     * 状态： 1.启用 2.停用
     */
    private Short status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 修改订单url
     */
    private String modifyUrl;
    /**
     * 通知订单更新url:订单支付后，通知其他渠道更新订单状态
     */
    private String informUrl;
    /**
     * 微信商户号
     */
    private String mchId;
    /**
     * 渠道方appid
     */
    private String appidRemote;
    /**
     * 渠道方secret
     */
    private String secretRemote;
    /**
     * 通知渠道方的bean名字
     */
    private String notifyBeanName;
    /**
     * 可供拉取数据的来源方id(逗号分隔)
     */
    private String pushSourceId;
    /**
     * 推送欠费给渠道方的url
     */
    private String pushOwerecUrl;

    /**
     * 删除欠费后同步删除记录给渠道方的url
     */
    private String deleteOwerecUrl;

    /**
     * 渠道公共参数（Json格式）
     */
    private String extentionKey;

}
