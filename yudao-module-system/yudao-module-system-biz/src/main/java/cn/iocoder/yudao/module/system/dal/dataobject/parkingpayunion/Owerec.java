package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 欠费记录表
 *
 * @author xiaojie.lin
 * @since 2023-04-06
 */
@TableName(value = "t_owerec", autoResultMap = true)
@EqualsAndHashCode
public class Owerec implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 欠费记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来源公司id
     */
    private Integer sourceId;

    /**
     * 第三方订单id
     */
    @NotNull(message = "thirdpartyOrderId不能为空")
    private String thirdpartyOrderId;

    /**
     * 车位编码
     */
    @NotNull(message = "parkcode不能为空")
    private String parkcode;

    /**
     * 停车开始时间
     */
    @NotNull(message = "startTime不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 停车结束时间
     */
    @NotNull(message = "closeTime不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime closeTime;


    @AssertTrue(message = "closeTime必须迟于startTime")
    public boolean isCloseTimeAfterStartTime() {
        if (closeTime == null || startTime == null) {
            // 如果有空值，不进行校验
            return true;
        }
        return closeTime.isAfter(startTime);
    }

    /**
     * 停车详细地址
     */
    private String parkingAddress;

    /**
     * 纬度
     */
    private Float lat;

    /**
     * 经度
     */
    private Float lng;

    /**
     * 停车场类型： 1：路侧停车  2：室内场库  3：室外场库
     */
    private Integer parkingType;

    /**
     * 车辆类型： 1小型车，2大型车，3新能源小型车，4新能源大型车，5其他
     */
    private Integer carType;

    /**
     * 车牌号码
     */
    @NotNull(message = "plateNum不能为空")
    private String plateNum;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 订单来源已付金额
     */
    private BigDecimal paidMoney;

    /**
     * 订单应付金额
     */
    @NotNull(message = "receivableMoney不能为空")
    private BigDecimal receivableMoney;

    /**
     * 订单欠费金额
     */
    @NotNull(message = "oweMoney不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "oweMoney必须大于0.0")
    private BigDecimal oweMoney;

    /**
     * 状态： 0:欠费  1:已追缴   2:放弃追缴
     */
    private Integer status;

    /**
     * 追缴渠道id
     */
    private Integer channelId;

    /**
     * 追缴时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime retrieveTime;

    /**
     * 追缴金额
     */
    private BigDecimal retrieveMoney;

    /**
     * 追缴付费方式
     */
    private Integer retrieveMode;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 分账编号
     */
    private Integer profitSharingId;

    /**
     * 推送渠道编号：多个用逗号隔开
     */
    private String pushChannelId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 车主姓名
     */
//    @TableField(typeHandler = EncryptHandler.class)
    private String name;

    /**
     * 车主手机号， 多个用逗号隔开
     */
//    @TableField(typeHandler = EncryptHandler.class)
    private String phone;

    /**
     * 照片id， 多个用逗号隔开（数据冗余）
     */
    private String photoId;

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

    public Integer getSourceId(){
        return sourceId;
    }

    public void setSourceId(Integer sourceId){
        this.sourceId = sourceId;
    }

    public String getThirdpartyOrderId(){
        return thirdpartyOrderId;
    }

    public void setThirdpartyOrderId(String thirdpartyOrderId){
        this.thirdpartyOrderId = thirdpartyOrderId;
    }

    public String getParkcode(){
        return parkcode;
    }

    public void setParkcode(String parkcode){
        this.parkcode = parkcode;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getCloseTime(){
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime){
        this.closeTime = closeTime;
    }

    public String getParkingAddress(){
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress){
        this.parkingAddress = parkingAddress;
    }

    public Float getLat(){
        return lat;
    }

    public void setLat(Float lat){
        this.lat = lat;
    }

    public Float getLng(){
        return lng;
    }

    public void setLng(Float lng){
        this.lng = lng;
    }

    public Integer getParkingType(){
        return parkingType;
    }

    public void setParkingType(Integer parkingType){
        this.parkingType = parkingType;
    }

    public Integer getCarType(){
        return carType;
    }

    public void setCarType(Integer carType){
        this.carType = carType;
    }

    public String getPlateNum(){
        return plateNum;
    }

    public void setPlateNum(String plateNum){
        this.plateNum = plateNum;
    }

    public String getPlateColor(){
        return plateColor;
    }

    public void setPlateColor(String plateColor){
        this.plateColor = plateColor;
    }

    public BigDecimal getPaidMoney(){
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney){
        this.paidMoney = paidMoney;
    }

    public BigDecimal getReceivableMoney(){
        return receivableMoney;
    }

    public void setReceivableMoney(BigDecimal receivableMoney){
        this.receivableMoney = receivableMoney;
    }

    public BigDecimal getOweMoney(){
        return oweMoney;
    }

    public void setOweMoney(BigDecimal oweMoney){
        this.oweMoney = oweMoney;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getChannelId(){
        return channelId;
    }

    public void setChannelId(Integer channelId){
        this.channelId = channelId;
    }

    public LocalDateTime getRetrieveTime(){
        return retrieveTime;
    }

    public void setRetrieveTime(LocalDateTime retrieveTime){
        this.retrieveTime = retrieveTime;
    }

    public BigDecimal getRetrieveMoney(){
        return retrieveMoney;
    }

    public void setRetrieveMoney(BigDecimal retrieveMoney){
        this.retrieveMoney = retrieveMoney;
    }

    public Integer getRetrieveMode(){
        return retrieveMode;
    }

    public void setRetrieveMode(Integer retrieveMode){
        this.retrieveMode = retrieveMode;
    }

    public String getOrderCode(){
        return orderCode;
    }

    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }

    public Integer getProfitSharingId(){
        return profitSharingId;
    }

    public void setProfitSharingId(Integer profitSharingId){
        this.profitSharingId = profitSharingId;
    }

    public String getPushChannelId(){
        return pushChannelId;
    }

    public void setPushChannelId(String pushChannelId){
        this.pushChannelId = pushChannelId;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhotoId(){
        return photoId;
    }

    public void setPhotoId(String photoId){
        this.photoId = photoId;
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
        return "Owerec{" +
                "id=" + id +
                ", sourceId=" + sourceId +
                ", thirdpartyOrderId=" + thirdpartyOrderId +
                ", parkcode=" + parkcode +
                ", startTime=" + startTime +
                ", closeTime=" + closeTime +
                ", parkingAddress=" + parkingAddress +
                ", lat=" + lat +
                ", lng=" + lng +
                ", parkingType=" + parkingType +
                ", carType=" + carType +
                ", plateNum=" + plateNum +
                ", plateColor=" + plateColor +
                ", paidMoney=" + paidMoney +
                ", receivableMoney=" + receivableMoney +
                ", oweMoney=" + oweMoney +
                ", status=" + status +
                ", channelId=" + channelId +
                ", retrieveTime=" + retrieveTime +
                ", retrieveMoney=" + retrieveMoney +
                ", retrieveMode=" + retrieveMode +
                ", orderCode=" + orderCode +
                ", profitSharingId=" + profitSharingId +
                ", pushChannelId=" + pushChannelId +
                ", remark=" + remark +
                ", phone=" + phone +
                ", photoId=" + photoId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
