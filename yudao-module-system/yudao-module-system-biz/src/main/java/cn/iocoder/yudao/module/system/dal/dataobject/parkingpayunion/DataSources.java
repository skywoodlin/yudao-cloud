package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 欠费数据来源公司表
 *
 * @author xiaojie.lin
 * @since 2023-06-26
 */
@TableName("t_data_sources")
@Data
@EqualsAndHashCode
public class DataSources implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统分配账户
     */
    private String appid;

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
     */
    private Integer status;

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
     */
    private Integer delFlag;

    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 通知来源方的bean名
     */
    private String notifySourceBeanName;

    /**
     *停车图片来源类型： 1： 从业务系统获取  2：本地t_parking_photo表获取
     */
    private Integer parkingPhotoSourceType ;

    /**
     *业务系统获取停车图片url-当parking_photo_source_type为1时生效
     */
    private String getParkingPhotoUrl;



    /**
     * 从业务系统批量获取停车图片url
     */
    private String batchGetParkingPhotoUrl;

    /**
     * 退款通知url
     */
    private String refundUrl;

    /**
     * 来源方是否提供了退款接口： 0. 未提供 1. 提供， 默认1
     */
    private Integer hasRefundApi;
}
