package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.DataSources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataSourcesMapper extends BaseMapperX<DataSources> {

    default DataSources selectByAppid(Integer appid) {
        return selectOne(DataSources::getAppid, appid);
    }

    default DataSources selectByPhone(String phone) {
        return selectOne(DataSources::getPhone, phone);
    }

//    default PageResult<DataSources> selectPage(UserPageReqVO reqVO, Collection<Long> deptIds) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<DataSources>()
//                .likeIfPresent(DataSources::getUsername, reqVO.getUsername())
//                .likeIfPresent(DataSources::getMobile, reqVO.getMobile())
//                .eqIfPresent(DataSources::getStatus, reqVO.getStatus())
//                .betweenIfPresent(DataSources::getCreateTime, reqVO.getCreateTime())
//                .inIfPresent(DataSources::getDeptId, deptIds)
//                .orderByDesc(DataSources::getId));
//    }

//    default List<DataSources> selectList(UserExportReqVO reqVO, Collection<Long> deptIds) {
//        return selectList(new LambdaQueryWrapperX<DataSources>()
//                .likeIfPresent(DataSources::getUsername, reqVO.getUsername())
//                .likeIfPresent(DataSources::getMobile, reqVO.getMobile())
//                .eqIfPresent(DataSources::getStatus, reqVO.getStatus())
//                .betweenIfPresent(DataSources::getCreateTime, reqVO.getCreateTime())
//                .inIfPresent(DataSources::getDeptId, deptIds));
//    }

    default List<DataSources> selectListByName(String name) {
        return selectList(new LambdaQueryWrapperX<DataSources>().like(DataSources::getName, name));
    }

    default List<DataSources> selectListByStatus(Integer status) {
        return selectList(DataSources::getStatus, status);
    }
//
//    default List<DataSources> selectListByDeptIds(Collection<Long> deptIds) {
//        return selectList(DataSources::getDeptId, deptIds);
//    }

}
