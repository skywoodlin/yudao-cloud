package cn.iocoder.yudao.module.system.dal.dataobject.arbi.businesstype;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 仲裁业务类型 DO
 *
 * @author 仲裁管理员
 */
@TableName("t_business_type")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTypeDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 业务名称
     */
    private String name;
    /**
     * 业务代码
     */
    private String code;
    /**
     * 仲裁所需要的文件类型
     */
    private String arbiNeedFile;

}
