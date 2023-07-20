package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 订单分账明细表
 *
 * @author xiaojie.lin
 * @since 2023-04-06
 */
@TableName("t_profit_sharing_info")
@EqualsAndHashCode
public class ProfitSharingInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * order id: t_order主键
     */
    private Integer orderId;

    /**
     * 欠费记录id
     */
    private Integer owerecId;

    /**
     * 分账金额
     */
    private BigDecimal sharingMoney;

    /**
     * 渠道分账金额
     */
    private BigDecimal channelMoney;

    /**
     * 来源分账金额
     */
    private BigDecimal sourceMoney;

    /**
     * 平台分账金额
     */
    private BigDecimal platformMoney;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 来源方id
     */
    private Integer sourceId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getOrderId(){
        return orderId;
    }

    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    public Integer getOwerecId(){
        return owerecId;
    }

    public void setOwerecId(Integer owerecId){
        this.owerecId = owerecId;
    }

    public BigDecimal getSharingMoney(){
        return sharingMoney;
    }

    public void setSharingMoney(BigDecimal sharingMoney){
        this.sharingMoney = sharingMoney;
    }

    public BigDecimal getChannelMoney(){
        return channelMoney;
    }

    public void setChannelMoney(BigDecimal channelMoney){
        this.channelMoney = channelMoney;
    }

    public BigDecimal getSourceMoney(){
        return sourceMoney;
    }

    public void setSourceMoney(BigDecimal sourceMoney){
        this.sourceMoney = sourceMoney;
    }

    public BigDecimal getPlatformMoney(){
        return platformMoney;
    }

    public void setPlatformMoney(BigDecimal platformMoney){
        this.platformMoney = platformMoney;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }

    public Integer getSourceId(){
        return sourceId;
    }

    public void setSourceId(Integer sourceId){
        this.sourceId = sourceId;
    }

    @Override
    public String toString(){
        return "ProfitSharingInfo{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", owerecId=" + owerecId +
                ", sharingMoney=" + sharingMoney +
                ", channelMoney=" + channelMoney +
                ", sourceMoney=" + sourceMoney +
                ", platformMoney=" + platformMoney +
                ", createTime=" + createTime +
                ", sourceId=" + sourceId +
                "}";
    }
}
