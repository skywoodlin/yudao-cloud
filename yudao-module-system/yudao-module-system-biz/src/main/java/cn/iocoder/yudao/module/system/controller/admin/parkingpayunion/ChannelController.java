package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelRespVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ChannelDO;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */
@Tag(name = "管理后台 - 渠道")
@RestController
@RequestMapping("/system/channel")
@Validated
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @PostMapping("/create")
    @Operation(summary = "创建渠道")
//    @PreAuthorize("@ss.hasPermission('system:channel:create')")
    public CommonResult<Integer> createChannel(@Valid @RequestBody ChannelSaveReqVO createReqVO) {
        return success(channelService.createChannel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新渠道")
//    @PreAuthorize("@ss.hasPermission('system:channel:update')")
    public CommonResult<Boolean> updateChannel(@Valid @RequestBody ChannelSaveReqVO updateReqVO) {
        channelService.updateChannel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除渠道")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('system:channel:delete')")
    public CommonResult<Boolean> deleteChannel(@RequestParam("id") Integer id) {
        channelService.deleteChannel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得渠道")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('system:channel:query')")
    public CommonResult<ChannelRespVO> getChannel(@RequestParam("id") Integer id) {
        ChannelDO channel = channelService.getChannel(id);
        return success(BeanUtils.toBean(channel, ChannelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得渠道分页")
//    @PreAuthorize("@ss.hasPermission('system:channel:query')")
    public CommonResult<PageResult<ChannelRespVO>> getChannelPage(@Valid ChannelPageReqVO pageReqVO) {
        PageResult<ChannelDO> pageResult = channelService.getChannelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChannelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出渠道 Excel")
//    @PreAuthorize("@ss.hasPermission('system:channel:export')")
    @OperateLog(type = EXPORT)
    public void exportChannelExcel(@Valid ChannelPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException{
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChannelDO> list = channelService.getChannelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "渠道.xls", "数据", ChannelRespVO.class,
                BeanUtils.toBean(list, ChannelRespVO.class));
    }

}
