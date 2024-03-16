package cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ListOwerecReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface OwerecMapper extends BaseMapperX<Owerec> {

        default PageResult<Owerec> selectPage(ListOwerecReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Owerec>()
                .eqIfPresent(Owerec::getSourceId, reqVO.getSourceId())
                .eqIfPresent(Owerec::getStatus, reqVO.getStatus())
                .eqIfPresent(Owerec::getPlateColor, reqVO.getPlateColor())
                .likeIfPresent(Owerec::getPlateNum, reqVO.getPlateNum())
                .eqIfNotBlank(Owerec::getThirdpartyOrderId, reqVO.getThirdpartyOrderId())
                .eqIfNotBlank(Owerec::getOrderCode, reqVO.getOrderCode())
                .betweenIfPresent(Owerec::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(Owerec::getRetrieveTime, reqVO.getRetrieveTime())
                .betweenIfPresent(Owerec::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(Owerec::getCloseTime, reqVO.getCloseTime())
                .orderByDesc(Owerec::getId));
    }

//    default Owerec selectByAppid(Integer appid) {
//        return selectOne(Owerec::getAppid, appid);
//    }

//    default Owerec selectByPhone(String phone) {
//        return selectOne(Owerec::getPhone, phone);
//    }



//    default List<Owerec> selectList(UserExportReqVO reqVO, Collection<Long> deptIds) {
//        return selectList(new LambdaQueryWrapperX<Owerec>()
//                .likeIfPresent(Owerec::getUsername, reqVO.getUsername())
//                .likeIfPresent(Owerec::getMobile, reqVO.getMobile())
//                .eqIfPresent(Owerec::getStatus, reqVO.getStatus())
//                .betweenIfPresent(Owerec::getCreateTime, reqVO.getCreateTime())
//                .inIfPresent(Owerec::getDeptId, deptIds));
//    }

    default List<Owerec> selectListByName(String name) {
        return selectList(new LambdaQueryWrapperX<Owerec>().like(Owerec::getName, name));
    }

    default List<Owerec> selectListByStatus(Integer status) {
        return selectList(Owerec::getStatus, status);
    }
//
//    default List<Owerec> selectListByDeptIds(Collection<Long> deptIds) {
//        return selectList(Owerec::getDeptId, deptIds);
//    }

}
