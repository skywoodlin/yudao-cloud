package cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */
@Schema(description = "管理后台 - 申请方的信息表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SourceApplicantInfoPageReqVO extends PageParam{

    @Schema(description = "作为申请方的公司名称")
    private String applicant;

    @Schema(description = "身份证号码或统一社会信用代码")
    private String identification;

    @Schema(description = "法人姓名-公司才有", example = "李四")
    private String legalPersonName;

    @Schema(description = "法人身份证号-公司才有", example = "19534")
    private String legalPersonId;

    @Schema(description = "个人电话或公司电话")
    private String phone;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
