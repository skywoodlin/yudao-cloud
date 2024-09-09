package cn.iocoder.yudao.module.system.controller.admin.common.vo;

import cn.iocoder.yudao.module.system.util.tools.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2024/5/30.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/30
 */
@Data
public class ParkingPayServiceRespVo{
    // code: 0成功， 其它失败
    private Integer status;

    // 返回的data
    private Object data;

    // 返回的msg
    private String message;

//    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private LocalDateTime timestamp;
}
