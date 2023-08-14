package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xiaojie.lin on 2023/8/3.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/8/3
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ImportOwerecVo{
    @ExcelProperty("第三方订单号")
    private String thirdPartyOrderId;

    @ExcelProperty("车牌号")
    private String plateNum;

    @ExcelProperty("应收金额")
    private BigDecimal receivableMoney;

    @ExcelProperty("欠费金额")
    private BigDecimal oweMoney;


    // 图片urls, 多张图片用";"号分割
    @ExcelProperty("停车图片")
    private String urlsString;

}
