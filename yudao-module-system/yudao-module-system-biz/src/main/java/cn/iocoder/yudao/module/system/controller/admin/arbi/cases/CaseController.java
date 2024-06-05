package cn.iocoder.yudao.module.system.controller.admin.arbi.cases;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CasePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.cases.vo.CaseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.cases.CaseDO;
import cn.iocoder.yudao.module.system.service.arbi.cases.CaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
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


@Tag(name = "管理后台 - 案件")
@RestController
@RequestMapping("/system/arbi/case")
@Validated
public class CaseController {

    @Resource
    private CaseService caseService;

    @PostMapping("/create")
    @Operation(summary = "创建案件")
    @PreAuthorize("@ss.hasPermission('system:case:create')")
    public CommonResult<Integer> createCase(@Valid @RequestBody CaseSaveReqVO createReqVO) {
        return success(caseService.createCase(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新案件")
    @PreAuthorize("@ss.hasPermission('system:case:update')")
    public CommonResult<Boolean> updateCase(@Valid @RequestBody CaseSaveReqVO updateReqVO) {
        caseService.updateCase(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除案件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:case:delete')")
    public CommonResult<Boolean> deleteCase(@RequestParam("id") Integer id) {
        caseService.deleteCase(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得案件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:case:query')")
    @SneakyThrows
    public CommonResult<CaseRespVO> getCase(@RequestParam("id") Integer id) {
//        CaseDO cases = caseService.getCase(id);
        CaseRespVO caseRespVO = caseService.getCaseInfo(id);
//        return success(BeanUtils.toBean(cases, CaseRespVO.class));
        return success(caseRespVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得案件分页")
    @PreAuthorize("@ss.hasPermission('system:case:query')")
    public CommonResult<PageResult<CaseRespVO>> getCasePage(@Valid CasePageReqVO pageReqVO) {
//        PageResult<CaseDO> pageResult = caseService.getCasePage(pageReqVO);
        PageResult<CaseRespVO> pageResult = caseService.getCasePage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, CaseRespVO.class));
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出案件 Excel")
    @PreAuthorize("@ss.hasPermission('system:case:export')")
    @OperateLog(type = EXPORT)
    public void exportCaseExcel(@Valid CasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<CaseDO> list = caseService.getCasePage(pageReqVO).getList();
        List<CaseRespVO> list = caseService.getCasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "案件.xls", "数据", CaseRespVO.class,
                list);
//        ExcelUtils.write(response, "案件.xls", "数据", CaseRespVO.class,
//                        BeanUtils.toBean(list, CaseRespVO.class));
    }

    @GetMapping("/syncToArbi")
    @Operation(summary = "同步案件信息到仲裁系统")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @SneakyThrows
    public CommonResult<Boolean> syncToArbi(@RequestParam("id") Integer id) {
        caseService.syncToArbi(id);
        return success(true);
    }

}