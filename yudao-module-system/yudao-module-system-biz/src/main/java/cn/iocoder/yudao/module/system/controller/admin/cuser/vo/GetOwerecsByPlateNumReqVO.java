package cn.iocoder.yudao.module.system.controller.admin.cuser.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import lombok.Data;

/**
 * Created by xiaojie.lin on 2023/10/13.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/10/13
 */
@Data
public class GetOwerecsByPlateNumReqVO extends PageParam{
    private String plateNum;
    private String plateColor;
}
