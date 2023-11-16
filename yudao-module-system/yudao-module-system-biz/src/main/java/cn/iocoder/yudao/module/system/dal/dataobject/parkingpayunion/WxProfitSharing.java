package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 微信分账表
 *
 * @author xiaojie.lin
 * @since 2023-11-14
 */
@TableName("t_wx_profit_sharing")
public class WxProfitSharing implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商户分账单号
     */
    private String outOrderNo;

    /**
     * 微信支付订单号
     */
    private String orderCode;

    /**
     * 分账金额
     */
    private BigDecimal orderMoney;

    /**
     * 枚举值：PROCESSING：处理中  FINISHED：分账完成
     */
    private String state;

    /**
     * 是否解冻剩余未分资金  0: 否，该笔订单剩余未分账的金额不会解冻回分账方商户，可以对该笔订单再次进行分账   1：是，该笔订单剩余未分账的金额会解冻回分账方商户
     */
    private Integer unfreezeUnsplit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getOutOrderNo(){
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo){
        this.outOrderNo = outOrderNo;
    }

    public String getOrderCode(){
        return orderCode;
    }

    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }

    public BigDecimal getOrderMoney(){
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney){
        this.orderMoney = orderMoney;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public Integer getUnfreezeUnsplit(){
        return unfreezeUnsplit;
    }

    public void setUnfreezeUnsplit(Integer unfreezeUnsplit){
        this.unfreezeUnsplit = unfreezeUnsplit;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }

    @Override
    public String toString(){
        return "WxProfitSharing{" +
                "id=" + id +
                ", outOrderNo=" + outOrderNo +
                ", orderCode=" + orderCode +
                ", orderMoney=" + orderMoney +
                ", state=" + state +
                ", unfreezeUnsplit=" + unfreezeUnsplit +
                ", remark=" + remark +
                ", createTime=" + createTime +
                "}";
    }
}
