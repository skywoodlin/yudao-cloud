package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分账结算表
 *
 * @author xiaojie.lin
 * @since 2023-07-17
 */
@TableName("t_profit_sharing_sum")
@EqualsAndHashCode
public class ProfitSharingSum implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 结算起止日期(yyyy-MM-dd - yyyy-MM-dd)
     */
    private String dateRange;

    /**
     * 计算笔数
     */
    private Integer counts;

    /**
     * 分账金额（交易金额）
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

    /**
     * 渠道方id
     */
    private Integer channelId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDateRange(){
        return dateRange;
    }

    public void setDateRange(String dateRange){
        this.dateRange = dateRange;
    }

    public Integer getCounts(){
        return counts;
    }

    public void setCounts(Integer counts){
        this.counts = counts;
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

    public Integer getChannelId(){
        return channelId;
    }

    public void setChannelId(Integer channelId){
        this.channelId = channelId;
    }

    @Override
    public String toString(){
        return "ProfitSharingSum{" +
                "id=" + id +
                ", dateRange=" + dateRange +
                ", counts=" + counts +
                ", sharingMoney=" + sharingMoney +
                ", channelMoney=" + channelMoney +
                ", sourceMoney=" + sourceMoney +
                ", platformMoney=" + platformMoney +
                ", createTime=" + createTime +
                ", sourceId=" + sourceId +
                ", channelId=" + channelId +
                "}";
    }
}
