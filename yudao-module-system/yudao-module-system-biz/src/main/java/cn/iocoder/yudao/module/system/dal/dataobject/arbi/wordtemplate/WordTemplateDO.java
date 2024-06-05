package cn.iocoder.yudao.module.system.dal.dataobject.arbi.wordtemplate;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 申请人模板配置 DO
 *
 * @author 仲裁管理员
 */
@TableName("t_word_template")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordTemplateDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 来源ID
     */
    private Integer sourcesId;
    /**
     * 模版填充内容
     */
    private String content;
    /**
     * 文件标题
     */
    private String title;
    /**
     * 模版名称
     */
    private String templateName;
    /**
     * 申请人表ID
     */
    private Integer applicantInfoId;

}