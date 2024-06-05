package cn.iocoder.yudao.module.system.service.arbi.cases.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by xiaojie.lin on 2024/6/4.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/4
 */
@Data
public class DocInfoVo {
    // 文件的中文名称
    private String title;
    private String title_en;
    private List<DocInfoMiniVo> fileList;
}
