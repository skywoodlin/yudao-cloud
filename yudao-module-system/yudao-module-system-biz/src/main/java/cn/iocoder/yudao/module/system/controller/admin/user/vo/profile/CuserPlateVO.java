package cn.iocoder.yudao.module.system.controller.admin.user.vo.profile;

import lombok.Data;

/**
 * Created by xiaojie.lin on 2023/10/8.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/10/8
 */
@Data
public class CuserPlateVO{
    private Integer id;

    /**
     * system_user表id
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 车牌
     */
    private String plateNum;

    /**
     * 颜色
     */
    private String plateColor;

    /**
     * 0: 未认证， 1： 已认证
     */
    private Integer verifiedStatus;

    /**
     * 行驶证照片url
     */
    private String carLicenseImageUrl;

    /**
     * 是否是当前选中的车牌： 0：否 1：是
     */
    private Integer active;

    /**
     * 审核信息：一般失败时才有用
     */
    private String verifyMsg;
}
