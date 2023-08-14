package cn.iocoder.yudao.module.system.controller.admin.datasources;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.datasources.DataSourcesDO;
import cn.iocoder.yudao.module.system.convert.datasources.DataSourcesConvert;
import cn.iocoder.yudao.module.system.service.datasources.DataSourcesService;

@Tag(name = "管理后台 - 欠费数据来源公司")
@RestController
@RequestMapping("/system/data-sources")
@Validated
public class DataSourcesController {

    @Resource
    private DataSourcesService dataSourcesService;

    @PostMapping("/create")
    @Operation(summary = "创建欠费数据来源公司")
    @PreAuthorize("@ss.hasPermission('system:data-sources:create')")
    public CommonResult<Integer> createDataSources(@Valid @RequestBody DataSourcesCreateReqVO createReqVO) {
        return success(dataSourcesService.createDataSources(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新欠费数据来源公司")
    @PreAuthorize("@ss.hasPermission('system:data-sources:update')")
    public CommonResult<Boolean> updateDataSources(@Valid @RequestBody DataSourcesUpdateReqVO updateReqVO) {
        dataSourcesService.updateDataSources(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除欠费数据来源公司")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:data-sources:delete')")
    public CommonResult<Boolean> deleteDataSources(@RequestParam("id") Integer id) {
        dataSourcesService.deleteDataSources(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得欠费数据来源公司")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:data-sources:query')")
    public CommonResult<DataSourcesRespVO> getDataSources(@RequestParam("id") Integer id) {
        DataSourcesDO dataSources = dataSourcesService.getDataSources(id);
        return success(DataSourcesConvert.INSTANCE.convert(dataSources));
    }

    @GetMapping("/list")
    @Operation(summary = "获得欠费数据来源公司列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:data-sources:query')")
    public CommonResult<List<DataSourcesRespVO>> getDataSourcesList(@RequestParam("ids") Collection<Integer> ids) {
        List<DataSourcesDO> list = dataSourcesService.getDataSourcesList(ids);
        return success(DataSourcesConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得欠费数据来源公司分页")
    @PreAuthorize("@ss.hasPermission('system:data-sources:query')")
    public CommonResult<PageResult<DataSourcesRespVO>> getDataSourcesPage(@Valid DataSourcesPageReqVO pageVO) {
        PageResult<DataSourcesDO> pageResult = dataSourcesService.getDataSourcesPage(pageVO);
        return success(DataSourcesConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出欠费数据来源公司 Excel")
    @PreAuthorize("@ss.hasPermission('system:data-sources:export')")
    @OperateLog(type = EXPORT)
    public void exportDataSourcesExcel(@Valid DataSourcesExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DataSourcesDO> list = dataSourcesService.getDataSourcesList(exportReqVO);
        // 导出 Excel
        List<DataSourcesExcelVO> datas = DataSourcesConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "欠费数据来源公司.xls", "数据", DataSourcesExcelVO.class, datas);
    }

}
