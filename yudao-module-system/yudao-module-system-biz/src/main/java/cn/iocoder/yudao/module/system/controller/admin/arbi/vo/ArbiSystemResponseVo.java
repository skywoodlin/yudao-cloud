package cn.iocoder.yudao.module.system.controller.admin.arbi.vo;

import lombok.Data;

/**
 * Created by xiaojie.lin on 2024/5/30.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/30
 */
@Data
public class ArbiSystemResponseVo{
    // code: 0成功， 其它失败
    private Integer code;

    // 返回的data
    private Object data;

    // 返回的msg
    private String msg;
}
