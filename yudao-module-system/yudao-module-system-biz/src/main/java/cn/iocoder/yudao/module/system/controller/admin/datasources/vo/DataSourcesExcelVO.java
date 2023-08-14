package cn.iocoder.yudao.module.system.controller.admin.datasources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 欠费数据来源公司 Excel VO
 *
 * @author skywoodlin
 */
@Data
public class DataSourcesExcelVO {

    @ExcelProperty("系统分配账户")
    private Integer appid;

    @ExcelProperty("系统分配密钥")
    private String secret;

    @ExcelProperty("公司名称")
    private String name;

    @ExcelProperty("使用渠道")
    private String channels;

    @ExcelProperty("公司地址")
    private String address;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("支付结果回调地址(销账用)： 追缴中台定义回调内容，数据来源公司对接接口，数据来源公司提供回调地址")
    private String payResultUrl;

    @ExcelProperty("分账编号")
    private Integer profitSharingId;

    @ExcelProperty(value = "状态： 0未启用  1启用  2弃用", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Short status;

    @ExcelProperty("此数据来源使用的支付标识")
    private Integer payMode;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty(value = "是否删除  0： 否， 1：是", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Short delFlag;

    @ExcelProperty("删除时间")
    private Date deleteTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
