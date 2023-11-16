package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 微信分账明细表
 *
 * @author xiaojie.lin
 * @since 2023-11-14
 */
@TableName("t_wx_profit_sharing_info")
public class WxProfitSharingInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信支付订单号
     */
    private String orderCode;

    /**
     * 商户分账单号
     */
    private String outOrderNo;

    /**
     * 分账金额
     */
    private BigDecimal amount;

    /**
     * 分账描述 1.收费分账  2.解冻给分账方
     */
    private String description;

    /**
     * 接收方类型  1: MERCHANT_ID：商户号  2: PERSONAL_OPENID：个人openid（由父商户APPID转换得到）
     */
    private Integer type;

    /**
     * 商户号： 默认小程序商户号
     */
    private String account;

    /**
     * 分账失败原因， 当分账结果result为CLOSED（已关闭）时，返回该字段
     * 1、ACCOUNT_ABNORMAL：分账接收账户异常 2、NO_RELATION：分账关系已解除 3、RECEIVER_HIGH_RISK：高风险接收方 4、RECEIVER_REAL_NAME_NOT_VERIFIED：接收方未实名
     * 5、NO_AUTH：分账权限已解除 6、RECEIVER_RECEIPT_LIMIT：接收方已达收款限额 7、PAYER_ACCOUNT_ABNORMAL：分出方账户异常
     */
    private Integer result;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 分账完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 分账明细单号
     */
    private String detailId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getOrderCode(){
        return orderCode;
    }

    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }

    public String getOutOrderNo(){
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo){
        this.outOrderNo = outOrderNo;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public String getAccount(){
        return account;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public Integer getResult(){
        return result;
    }

    public void setResult(Integer result){
        this.result = result;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }

    public LocalDateTime getFinishTime(){
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime){
        this.finishTime = finishTime;
    }

    public String getDetailId(){
        return detailId;
    }

    public void setDetailId(String detailId){
        this.detailId = detailId;
    }

    @Override
    public String toString(){
        return "WxProfitSharingInfo{" +
                "id=" + id +
                ", orderCode=" + orderCode +
                ", outOrderNo=" + outOrderNo +
                ", amount=" + amount +
                ", description=" + description +
                ", type=" + type +
                ", account=" + account +
                ", result=" + result +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", detailId=" + detailId +
                "}";
    }
}
