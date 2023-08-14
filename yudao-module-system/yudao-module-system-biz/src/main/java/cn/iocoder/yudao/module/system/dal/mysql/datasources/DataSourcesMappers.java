package cn.iocoder.yudao.module.system.dal.mysql.datasources;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.datasources.DataSourcesDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.datasources.vo.*;

/**
 * 欠费数据来源公司 Mapper
 *
 * @author skywoodlin
 */
@Mapper
public interface DataSourcesMappers extends BaseMapperX<DataSourcesDO> {

    default PageResult<DataSourcesDO> selectPage(DataSourcesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DataSourcesDO>()
                .eqIfPresent(DataSourcesDO::getAppid, reqVO.getAppid())
                .likeIfPresent(DataSourcesDO::getName, reqVO.getName())
                .eqIfPresent(DataSourcesDO::getProfitSharingId, reqVO.getProfitSharingId())
                .eqIfPresent(DataSourcesDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DataSourcesDO::getDelFlag, reqVO.getDelFlag())
                .betweenIfPresent(DataSourcesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DataSourcesDO::getId));
    }

    default List<DataSourcesDO> selectList(DataSourcesExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DataSourcesDO>()
                .eqIfPresent(DataSourcesDO::getAppid, reqVO.getAppid())
                .likeIfPresent(DataSourcesDO::getName, reqVO.getName())
                .eqIfPresent(DataSourcesDO::getProfitSharingId, reqVO.getProfitSharingId())
                .eqIfPresent(DataSourcesDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DataSourcesDO::getDelFlag, reqVO.getDelFlag())
                .betweenIfPresent(DataSourcesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DataSourcesDO::getId));
    }

}
