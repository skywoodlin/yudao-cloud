package cn.iocoder.yudao.module.system.service.datasources;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.datasources.DataSourcesDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 欠费数据来源公司 Service 接口
 *
 * @author skywoodlin
 */
public interface DataSourcesService {

    /**
     * 创建欠费数据来源公司
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createDataSources(@Valid DataSourcesCreateReqVO createReqVO);

    /**
     * 更新欠费数据来源公司
     *
     * @param updateReqVO 更新信息
     */
    void updateDataSources(@Valid DataSourcesUpdateReqVO updateReqVO);

    /**
     * 删除欠费数据来源公司
     *
     * @param id 编号
     */
    void deleteDataSources(Integer id);

    /**
     * 获得欠费数据来源公司
     *
     * @param id 编号
     * @return 欠费数据来源公司
     */
    DataSourcesDO getDataSources(Integer id);

    /**
     * 获得欠费数据来源公司列表
     *
     * @param ids 编号
     * @return 欠费数据来源公司列表
     */
    List<DataSourcesDO> getDataSourcesList(Collection<Integer> ids);

    /**
     * 获得欠费数据来源公司分页
     *
     * @param pageReqVO 分页查询
     * @return 欠费数据来源公司分页
     */
    PageResult<DataSourcesDO> getDataSourcesPage(DataSourcesPageReqVO pageReqVO);

    /**
     * 获得欠费数据来源公司列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 欠费数据来源公司列表
     */
    List<DataSourcesDO> getDataSourcesList(DataSourcesExportReqVO exportReqVO);

}
