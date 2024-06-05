package cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo;

import lombok.Data;

/**
 * Created by xiaojie.lin on 2024/5/31.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/31
 */
@Data
public class SpecificColumnVo {
    /**
     * 案件类型： 1：停车欠费 2：待定
     */
    private Integer caseType;

    /**
     * 不同案件类型特有的值
     */
    private Object specificValue;


}
