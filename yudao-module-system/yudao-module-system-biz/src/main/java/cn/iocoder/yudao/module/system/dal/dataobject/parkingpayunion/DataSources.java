package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getAppid(){
        return appid;
    }

    public void setAppid(Integer appid){
        this.appid = appid;
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getChannels(){
        return channels;
    }

    public void setChannels(String channels){
        this.channels = channels;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPayResultUrl(){
        return payResultUrl;
    }

    public void setPayResultUrl(String payResultUrl){
        this.payResultUrl = payResultUrl;
    }

    public Integer getProfitSharingId(){
        return profitSharingId;
    }

    public void setProfitSharingId(Integer profitSharingId){
        this.profitSharingId = profitSharingId;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getPayMode(){
        return payMode;
    }

    public void setPayMode(Integer payMode){
        this.payMode = payMode;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public Integer getDelFlag(){
        return delFlag;
    }

    public void setDelFlag(Integer delFlag){
        this.delFlag = delFlag;
    }

    public LocalDateTime getDeleteTime(){
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime){
        this.deleteTime = deleteTime;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime){
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){
        return "DataSources{" +
                "id=" + id +
                ", appid=" + appid +
                ", secret=" + secret +
                ", name=" + name +
                ", channels=" + channels +
                ", address=" + address +
                ", phone=" + phone +
                ", payResultUrl=" + payResultUrl +
                ", profitSharingId=" + profitSharingId +
                ", status=" + status +
                ", payMode=" + payMode +
                ", remark=" + remark +
                ", delFlag=" + delFlag +
                ", deleteTime=" + deleteTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
