package cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplatePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateRespVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.wordtemplate.vo.WordTemplateSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.wordtemplate.WordTemplateDO;
import cn.iocoder.yudao.module.system.service.arbi.wordtemplate.WordTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "管理后台 - 申请人模板配置")
@RestController
@RequestMapping("/system/arbi/word-template")
@Validated
public class WordTemplateController {

    @Resource
    private WordTemplateService wordTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建申请人模板配置")
    @PreAuthorize("@ss.hasPermission('system:word-template:create')")
    public CommonResult<Integer> createWordTemplate(@Valid @RequestBody WordTemplateSaveReqVO createReqVO) {
        return success(wordTemplateService.createWordTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申请人模板配置")
    @PreAuthorize("@ss.hasPermission('system:word-template:update')")
    public CommonResult<Boolean> updateWordTemplate(@Valid @RequestBody WordTemplateSaveReqVO updateReqVO) {
        wordTemplateService.updateWordTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申请人模板配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:word-template:delete')")
    public CommonResult<Boolean> deleteWordTemplate(@RequestParam("id") Integer id) {
        wordTemplateService.deleteWordTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申请人模板配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:word-template:query')")
    public CommonResult<WordTemplateRespVO> getWordTemplate(@RequestParam("id") Integer id) throws Exception{
//        WordTemplateDO wordTemplate = wordTemplateService.getWordTemplate(id);
//        return success(BeanUtils.toBean(wordTemplate, WordTemplateRespVO.class));
        WordTemplateRespVO wordTemplate = wordTemplateService.getWordTemplateInfo(id);
        return success(BeanUtils.toBean(wordTemplate, WordTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申请人模板配置分页")
    @PreAuthorize("@ss.hasPermission('system:word-template:query')")
    public CommonResult<PageResult<WordTemplateRespVO>> getWordTemplatePage(@Valid WordTemplatePageReqVO pageReqVO) {
//        PageResult<WordTemplateDO> pageResult = wordTemplateService.getWordTemplatePage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, WordTemplateRespVO.class));
        PageResult<WordTemplateRespVO> pageResult = wordTemplateService.getWordTemplatePage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申请人模板配置 Excel")
    @PreAuthorize("@ss.hasPermission('system:word-template:export')")
    @OperateLog(type = EXPORT)
    public void exportWordTemplateExcel(@Valid WordTemplatePageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException{
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<WordTemplateDO> list = wordTemplateService.getWordTemplatePage(pageReqVO).getList();
        List<WordTemplateRespVO> list = wordTemplateService.getWordTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申请人模板配置.xls", "数据", WordTemplateRespVO.class,
                list);
    }
}
