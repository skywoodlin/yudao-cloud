package cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.businesstype.BusinessTypeDO;
import cn.iocoder.yudao.module.system.service.arbi.businesstype.BusinessTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Tag(name = "管理后台 - 仲裁业务类型")
@RestController
@RequestMapping("/system/arbi/business-type")
@Validated
@Slf4j
public class BusinessTypeController {

    @Resource
    private BusinessTypeService businessTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建仲裁业务类型")
    @PreAuthorize("@ss.hasPermission('arbi:business-type:create')")
    public CommonResult<Integer> createBusinessType(@Valid @RequestBody BusinessTypeSaveReqVO createReqVO) {
        return success(businessTypeService.createBusinessType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新仲裁业务类型")
    @PreAuthorize("@ss.hasPermission('arbi:business-type:update')")
    public CommonResult<Boolean> updateBusinessType(@Valid @RequestBody BusinessTypeSaveReqVO updateReqVO) {
        businessTypeService.updateBusinessType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除仲裁业务类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('arbi:business-type:delete')")
    public CommonResult<Boolean> deleteBusinessType(@RequestParam("id") Integer id) {
        businessTypeService.deleteBusinessType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得仲裁业务类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('arbi:business-type:query')")
    public CommonResult<BusinessTypeRespVO> getBusinessType(@RequestParam("id") Integer id) {
        BusinessTypeDO businessType = businessTypeService.getBusinessType(id);
        return success(BeanUtils.toBean(businessType, BusinessTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得仲裁业务类型分页")
    @PreAuthorize("@ss.hasPermission('arbi:business-type:query')")
    public CommonResult<PageResult<BusinessTypeRespVO>> getBusinessTypePage(@Valid BusinessTypePageReqVO pageReqVO) {
        PageResult<BusinessTypeDO> pageResult = businessTypeService.getBusinessTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BusinessTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出仲裁业务类型 Excel")
    @PreAuthorize("@ss.hasPermission('arbi:business-type:export')")
    @OperateLog(type = EXPORT)
    public void exportBusinessTypeExcel(@Valid BusinessTypePageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BusinessTypeDO> list = businessTypeService.getBusinessTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "仲裁业务类型.xls", "数据", BusinessTypeRespVO.class,
                BeanUtils.toBean(list, BusinessTypeRespVO.class));
    }

}