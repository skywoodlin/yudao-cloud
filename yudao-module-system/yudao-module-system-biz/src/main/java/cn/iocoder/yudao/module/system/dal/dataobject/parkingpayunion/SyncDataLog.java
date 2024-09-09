package cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 同步数据日志表
 *
 * @author xiaojie.lin
 * @since 2023-04-10
 */
@TableName("t_sync_data_log")
@EqualsAndHashCode
public class SyncDataLog implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 欠费记录id
     */
    @NotNull(message = "owerecId不能为空")
    private Integer owerecId;

    /**
     * 渠道id
     */
    @NotNull(message = "channelId不能为空")
    private Integer channelId;

    /**
     * 推送时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime syncTime;

    /**
     * 推送状态： 0.推送失败 1.推送成功
     */
    private Integer syncStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String syncMsg;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getOwerecId(){
        return owerecId;
    }

    public void setOwerecId(Integer owerecId){
        this.owerecId = owerecId;
    }

    public Integer getChannelId(){
        return channelId;
    }

    public void setChannelId(Integer channelId){
        this.channelId = channelId;
    }

    public LocalDateTime getSyncTime(){
        return syncTime;
    }

    public void setSyncTime(LocalDateTime syncTime){
        this.syncTime = syncTime;
    }

    public Integer getSyncStatus(){
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus){
        this.syncStatus = syncStatus;
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

    public String getSyncMsg(){
        return syncMsg;
    }

    public void setSyncMsg(String syncMsg){
        this.syncMsg = syncMsg;
    }

    @Override
    public String toString(){
        return "SyncDataLog{" +
                "id=" + id +
                ", owrrecId=" + owerecId +
                ", channelId=" + channelId +
                ", syncTime=" + syncTime +
                ", syncStatus=" + syncStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", syncMsg=" + syncMsg +
                "}";
    }
}
