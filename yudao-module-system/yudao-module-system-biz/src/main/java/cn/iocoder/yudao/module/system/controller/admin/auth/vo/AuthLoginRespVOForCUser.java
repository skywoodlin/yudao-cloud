package cn.iocoder.yudao.module.system.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginRespVOForCUser{

    @Schema(description = "用户编号", required = true, example = "1024")
    private Long userId;

    @Schema(description = "访问令牌", required = true, example = "happy")
    private String accessToken;

    @Schema(description = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    @Schema(description = "过期时间", required = true)
    private LocalDateTime expiresTime;

    @Schema(description = "行驶证照片url， 如果有， 没有的话为null", example = "http://abc.com/12345678")
    private String carLicenseImageUrl;

    @Schema(description = "行驶证是否认证， 0: 未认证， 1： 已认证, 刚注册没上传的话为0， 一定有值", example = "true")
    private Integer verifiedStatus;

    @Schema(description = "车牌号，如果有，没有的话为null", example = "闽D12345")
    private String plateNum;

    @Schema(description = "车牌颜色，如果有，没有的话为null", example = "蓝")
    private String plateColor;

    //    @Schema(description = "是否有审核行驶证的权限", example = "true")
//    private Boolean hasAuthToApproval;
    @Schema(description = "角色Id: 999:车主  998：审核员  997：admin", example = "999")
    private Integer roleId;
}