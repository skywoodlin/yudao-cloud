package cn.iocoder.yudao.module.system.service.parkingpayunion;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ChannelDO;

import javax.validation.Valid;

/**
 * 渠道 Service 接口
 *
 * @author 诉前管理员
 */
public interface ChannelService {

    /**
     * 创建渠道
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChannel(@Valid ChannelSaveReqVO createReqVO);

    /**
     * 更新渠道
     *
     * @param updateReqVO 更新信息
     */
    void updateChannel(@Valid ChannelSaveReqVO updateReqVO);

    /**
     * 删除渠道
     *
     * @param id 编号
     */
    void deleteChannel(Integer id);

    /**
     * 获得渠道
     *
     * @param id 编号
     * @return 渠道
     */
    ChannelDO getChannel(Integer id);

    /**
     * 获得渠道分页
     *
     * @param pageReqVO 分页查询
     * @return 渠道分页
     */
    PageResult<ChannelDO> getChannelPage(ChannelPageReqVO pageReqVO);

}
