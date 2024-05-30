package cn.iocoder.yudao.module.system.service.arbi.businesstype;

/**
 * Created by xiaojie.lin on 2024/5/21.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/21
 */

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeOptionsVo;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.arbi.businesstype.vo.BusinessTypeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.arbi.businesstype.BusinessTypeDO;
import cn.iocoder.yudao.module.system.dal.mysql.arbi.businesstype.BusinessTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.BUSINESS_TYPE_NOT_EXISTS;

/**
 * 仲裁业务类型 Service 实现类
 *
 * @author 仲裁管理员
 */
@Service
@Validated
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Resource
    private BusinessTypeMapper businessTypeMapper;

    @Override
    public Integer createBusinessType(BusinessTypeSaveReqVO createReqVO) {
        // 插入
        BusinessTypeDO businessType = BeanUtils.toBean(createReqVO, BusinessTypeDO.class);
        businessTypeMapper.insert(businessType);
        // 返回
        return businessType.getId();
    }

    @Override
    public void updateBusinessType(BusinessTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateBusinessTypeExists(updateReqVO.getId());
        // 更新
        BusinessTypeDO updateObj = BeanUtils.toBean(updateReqVO, BusinessTypeDO.class);
        businessTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteBusinessType(Integer id) {
        // 校验存在
        validateBusinessTypeExists(id);
        // 删除
        businessTypeMapper.deleteById(id);
    }

    private void validateBusinessTypeExists(Integer id) {
        if (businessTypeMapper.selectById(id) == null) {
            throw exception(BUSINESS_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public BusinessTypeDO getBusinessType(Integer id) {
        return businessTypeMapper.selectById(id);
    }

    @Override
    public PageResult<BusinessTypeDO> getBusinessTypePage(BusinessTypePageReqVO pageReqVO) {
        return businessTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BusinessTypeOptionsVo> getBusinessTypeOptions(){
        return businessTypeMapper.getBusinessTypeOptions();
    }

}
