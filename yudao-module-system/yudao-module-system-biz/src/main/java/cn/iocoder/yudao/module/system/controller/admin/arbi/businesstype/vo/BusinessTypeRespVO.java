package cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 仲裁业务类型 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BusinessTypeRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32744")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "业务名称", example = "张三")
    @ExcelProperty("业务名称")
    private String name;

    @Schema(description = "业务代码")
    @ExcelProperty("业务代码")
    private String code;

    @Schema(description = "仲裁所需要的文件类型")
    @ExcelProperty("仲裁所需要的文件类型")
    private String arbiNeedFile;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
