package cn.iocoder.yudao.module.system.service.datasources;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.DataSourcesCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.DataSourcesExportReqVO;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.DataSourcesPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.DataSourcesUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.datasources.DataSourcesConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.datasources.DataSourcesDO;
import cn.iocoder.yudao.module.system.dal.mysql.datasources.DataSourcesMappers;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.DATA_SOURCES_NOT_EXISTS;

/**
 * 欠费数据来源公司 Service 实现类
 *
 * @author skywoodlin
 */
@Service
@Validated
public class DataSourcesServiceImpl implements DataSourcesService {

    @Resource
    private DataSourcesMappers dataSourcesMappers;

    @Override
    public Integer createDataSources(DataSourcesCreateReqVO createReqVO) {
        // 插入
        DataSourcesDO dataSources = DataSourcesConvert.INSTANCE.convert(createReqVO);
        dataSourcesMappers.insert(dataSources);
        // 返回
        return dataSources.getId();
    }

    @Override
    public void updateDataSources(DataSourcesUpdateReqVO updateReqVO) {
        // 校验存在
        validateDataSourcesExists(updateReqVO.getId());
        // 更新
        DataSourcesDO updateObj = DataSourcesConvert.INSTANCE.convert(updateReqVO);
        dataSourcesMappers.updateById(updateObj);
    }

    @Override
    public void deleteDataSources(Integer id) {
        // 校验存在
        validateDataSourcesExists(id);
        // 删除
        dataSourcesMappers.deleteById(id);
    }

    private void validateDataSourcesExists(Integer id) {
        if (dataSourcesMappers.selectById(id) == null) {
            throw exception(DATA_SOURCES_NOT_EXISTS);
        }
    }

    @Override
    public DataSourcesDO getDataSources(Integer id) {
        return dataSourcesMappers.selectById(id);
    }

    @Override
    public List<DataSourcesDO> getDataSourcesList(Collection<Integer> ids) {
        return dataSourcesMappers.selectBatchIds(ids);
    }

    @Override
    public PageResult<DataSourcesDO> getDataSourcesPage(DataSourcesPageReqVO pageReqVO) {
        return dataSourcesMappers.selectPage(pageReqVO);
    }

    @Override
    public List<DataSourcesDO> getDataSourcesList(DataSourcesExportReqVO exportReqVO) {
        return dataSourcesMappers.selectList(exportReqVO);
    }

}
