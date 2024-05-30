package cn.iocoder.yudao.module.system.service.arbi.businesstype;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeOptionsVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.businesstype.BusinessTypeDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 仲裁业务类型 Service 接口
 *
 * @author 仲裁管理员
 */
public interface BusinessTypeService {

    /**
     * 创建仲裁业务类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createBusinessType(@Valid BusinessTypeSaveReqVO createReqVO);

    /**
     * 更新仲裁业务类型
     *
     * @param updateReqVO 更新信息
     */
    void updateBusinessType(@Valid BusinessTypeSaveReqVO updateReqVO);

    /**
     * 删除仲裁业务类型
     *
     * @param id 编号
     */
    void deleteBusinessType(Integer id);

    /**
     * 获得仲裁业务类型
     *
     * @param id 编号
     * @return 仲裁业务类型
     */
    BusinessTypeDO getBusinessType(Integer id);

    /**
     * 获得仲裁业务类型分页
     *
     * @param pageReqVO 分页查询
     * @return 仲裁业务类型分页
     */
    PageResult<BusinessTypeDO> getBusinessTypePage(BusinessTypePageReqVO pageReqVO);

    /**
     * 获取业务类型下拉菜单
     * @return
     */
    List<BusinessTypeOptionsVo> getBusinessTypeOptions();

}
