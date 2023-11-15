package cn.iocoder.yudao.module.system.controller.admin.user.vo.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - C端用户个人中心信息 Response VO")
public class CUserProfileRespVO {

    @Schema(description = "用户id", example = "1")
    private Long userId;

    @Schema(description = "用户手机", example = "18900000000")
    private String mobile;

//    @Schema(description = "行驶证照片url， 如果有， 没有的话为null", example = "http://abc.com/12345678")
//    private String carLicenseImageUrl;
//
//    @Schema(description = "行驶证是否认证， 0: 未认证， 1： 已认证, 刚注册没上传的话为0， 一定有值", example = "true")
//    private Integer verifiedStatus;
//
//    @Schema(description = "车牌号，如果有，没有的话为null", example = "闽D12345")
//    private String plateNum;
//
//    @Schema(description = "车牌颜色，如果有，没有的话为null", example = "蓝")
//    private String plateColor;

//    @Schema(description = "是否有审核行驶证的权限", example = "true")
//    private Boolean hasAuthToApproval;
    @Schema(description = "角色Id: 999:车主  998：审核员  997：admin", example = "999")
    private Integer roleId;

    @Schema(description = "用户车牌信息列表", example = "")
    private List<CuserPlateVO> cuserPlateVOList;
}