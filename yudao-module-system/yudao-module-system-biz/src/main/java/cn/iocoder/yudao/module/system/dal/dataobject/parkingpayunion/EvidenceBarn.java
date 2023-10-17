package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2023/10/17.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/10/17
 */
@TableName(value = "t_evidence_barn", autoResultMap = true)
@EqualsAndHashCode
public class EvidenceBarn implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌号
     */
    private String plateNum;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 证据类型： 1：现场照片 2：短信 3：电话路由
     */
    private Integer eviType;

    /**
     * 证据内容：短信内容，现场照片url， mp3文档url
     */
    private String eviContent;

    /**
     * 证据时间
     */
    private LocalDateTime eviTime;

    /**
     * 第三方订单id
     */
    private String thirdpartyOrderId;

    /**
     * 区域
     */
    private String area;

    /**
     * 路段
     */
    private String road;

    /**
     * 车位号
     */
    private String parkCode;

    /**
     * 来源公司id
     */
    private Integer sourceId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public Integer getEviType(){
        return eviType;
    }

    public void setEviType(Integer eviType){
        this.eviType = eviType;
    }

    public String getEviContent(){
        return eviContent;
    }

    public void setEviContent(String eviContent){
        this.eviContent = eviContent;
    }

    public LocalDateTime getEviTime(){
        return eviTime;
    }

    public void setEviTime(LocalDateTime eviTime){
        this.eviTime = eviTime;
    }

    public String getThirdpartyOrderId(){
        return thirdpartyOrderId;
    }

    public void setThirdpartyOrderId(String thirdpartyOrderId){
        this.thirdpartyOrderId = thirdpartyOrderId;
    }

    public String getArea(){
        return area;
    }

    public void setArea(String area){
        this.area = area;
    }

    public String getRoad(){
        return road;
    }

    public void setRoad(String road){
        this.road = road;
    }

    public String getParkCode(){
        return parkCode;
    }

    public void setParkCode(String parkCode){
        this.parkCode = parkCode;
    }

    public Integer getSourceId(){
        return sourceId;
    }

    public void setSourceId(Integer sourceId){
        this.sourceId = sourceId;
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
        return "EvidenceBarn{" +
                "id=" + id +
                ", plateNum=" + plateNum +
                ", plateColor=" + plateColor +
                ", eviType=" + eviType +
                ", eviContent=" + eviContent +
                ", eviTime=" + eviTime +
                ", thirdpartyOrderId=" + thirdpartyOrderId +
                ", area=" + area +
                ", road=" + road +
                ", parkCode=" + parkCode +
                ", sourceId=" + sourceId +
                ", createTime=" + createTime +
                "}";
    }
}
