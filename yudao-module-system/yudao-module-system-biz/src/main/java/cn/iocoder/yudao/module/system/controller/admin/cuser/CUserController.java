package cn.iocoder.yudao.module.system.controller.admin.cuser;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.framework.security.config.SecurityProperties;
import cn.iocoder.yudao.module.system.controller.admin.cuser.vo.GetOwerecsByPlateNumReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecVo;
import cn.iocoder.yudao.module.system.service.auth.AdminAuthService;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ParkingPayUnionService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import cn.iocoder.yudao.module.system.service.permission.RoleService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name =  "管理后台 - C店用户")
@RestController
@RequestMapping("/system/cuser")
@Validated
@Slf4j
public class CUserController{

    @Resource
    private AdminAuthService authService;
    @Resource
    private AdminUserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private ParkingPayUnionService parkingPayUnionService;

    @Value("${parkingInfoEntryService.url}")
    private String parkingInfoEntryServiceUrl;

    @PostMapping("/getOwerecByPlateNum")
    @Operation(summary = "获取车牌对应的停车欠费记录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<PageResult<ListOwerecVo>> getOwerecByPlateNum(@RequestBody GetOwerecsByPlateNumReqVO reqVO) throws Exception{
        if(reqVO.getPlateNum() == null || reqVO.getPlateColor() == null) {
            throw exception(new ErrorCode(1001003009, "必须传入车牌号码和车牌颜色"));
        }

        PageResult<ListOwerecVo> pageResult =  parkingPayUnionService.listOwerecForCUser(reqVO);
        return success(pageResult);
    }
}