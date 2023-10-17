package cn.iocoder.yudao.module.system.controller.admin.user.vo.evidence;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by xiaojie.lin on 2023/10/8.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/10/8
 */
@Data
public class EvidenceBarn{
    private Integer id;

    /**
     * 车牌号
     */
    private String plateNum;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 证据类型： 1：现场照片 2：短信 3：电话路由
     */
    private Integer eviType;

    /**
     * 证据内容：短信内容，现场照片url， mp3文档url
     */
    private String eviContent;

    /**
     * 证据时间
     */
    private LocalDateTime eviTime;

    /**
     * 区域
     */
    private String area;

    /**
     * 路段
     */
    private String road;

    /**
     * 车位号
     */
    private String parkCode;

    /**
     * 来源公司id
     */
    private Integer sourceId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
