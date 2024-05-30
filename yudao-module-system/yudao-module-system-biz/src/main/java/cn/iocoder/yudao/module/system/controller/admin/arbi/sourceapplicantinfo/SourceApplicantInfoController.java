package cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.annotation.SeaWeedUrlReplacement;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeOptionsVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.sourceapplicantinfo.SourceApplicantInfoDO;
import cn.iocoder.yudao.module.system.service.arbi.businesstype.BusinessTypeService;
import cn.iocoder.yudao.module.system.service.arbi.sourceapplicantinfo.SourceApplicantInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
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

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */
@Tag(name = "管理后台 - 申请人的信息表")
@RestController
@RequestMapping("/system/arbi/source-applicant-info")
@Validated
public class SourceApplicantInfoController {

    @Resource
    private SourceApplicantInfoService sourceApplicantInfoService;

    @Resource
    private BusinessTypeService businessTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建申请人的信息表")
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:create')")
    public CommonResult<Integer> createSourceApplicantInfo(@Valid @RequestBody SourceApplicantInfoSaveReqVO createReqVO) {
        return success(sourceApplicantInfoService.createSourceApplicantInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申请人的信息表")
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:update')")
    public CommonResult<Boolean> updateSourceApplicantInfo(@Valid @RequestBody SourceApplicantInfoSaveReqVO updateReqVO) {
        sourceApplicantInfoService.updateSourceApplicantInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申请人的信息表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:delete')")
    public CommonResult<Boolean> deleteSourceApplicantInfo(@RequestParam("id") Integer id) {
        sourceApplicantInfoService.deleteSourceApplicantInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申请人的信息表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:query')")
    public CommonResult<SourceApplicantInfoRespVO> getSourceApplicantInfo(@RequestParam("id") Integer id) {
        SourceApplicantInfoDO sourceApplicantInfo = sourceApplicantInfoService.getSourceApplicantInfo(id);
//        return success(BeanUtils.toBean(sourceApplicantInfo, SourceApplicantInfoRespVO.class));
        return success(sourceApplicantInfoService.covertToSourceApplicantInfoRespVO(sourceApplicantInfo));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申请人的信息表分页")
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:query')")
    public CommonResult<PageResult<SourceApplicantInfoRespVO>> getSourceApplicantInfoPage(@Valid SourceApplicantInfoPageReqVO pageReqVO) {
        PageResult<SourceApplicantInfoDO> pageResult = sourceApplicantInfoService.getSourceApplicantInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SourceApplicantInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申请人的信息表 Excel")
    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:export')")
    @OperateLog(type = EXPORT)
    public void exportSourceApplicantInfoExcel(@Valid SourceApplicantInfoPageReqVO pageReqVO,
                                               HttpServletResponse response) throws IOException{
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SourceApplicantInfoDO> list = sourceApplicantInfoService.getSourceApplicantInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申请人的信息表.xls", "数据", SourceApplicantInfoRespVO.class,
                BeanUtils.toBean(list, SourceApplicantInfoRespVO.class));
    }

    @GetMapping("/getBusinessTypeOptions")
    public List<BusinessTypeOptionsVo> getBusinessTypeOptions() {
        List<BusinessTypeOptionsVo> result = businessTypeService.getBusinessTypeOptions();
        return result;
    }


    @GetMapping("/syncToArbi")
    @Operation(summary = "同步申请人记录到仲裁系统")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @SneakyThrows
//    @PreAuthorize("@ss.hasPermission('system:source-applicant-info:update')")
    public CommonResult<Boolean> syncToArbi(@RequestParam("id") Integer id) {
//        sourceApplicantInfoService.updateSourceApplicantInfo(updateReqVO);
        sourceApplicantInfoService.syncToArbi(id);
        return success(true);
    }




}