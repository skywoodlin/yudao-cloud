package cn.iocoder.yudao.module.system.convert.datasources;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.datasources.DataSourcesDO;

/**
 * 欠费数据来源公司 Convert
 *
 * @author skywoodlin
 */
@Mapper
public interface DataSourcesConvert {

    DataSourcesConvert INSTANCE = Mappers.getMapper(DataSourcesConvert.class);

    DataSourcesDO convert(DataSourcesCreateReqVO bean);

    DataSourcesDO convert(DataSourcesUpdateReqVO bean);

    DataSourcesRespVO convert(DataSourcesDO bean);

    List<DataSourcesRespVO> convertList(List<DataSourcesDO> list);

    PageResult<DataSourcesRespVO> convertPage(PageResult<DataSourcesDO> page);

    List<DataSourcesExcelVO> convertList02(List<DataSourcesDO> list);

}
