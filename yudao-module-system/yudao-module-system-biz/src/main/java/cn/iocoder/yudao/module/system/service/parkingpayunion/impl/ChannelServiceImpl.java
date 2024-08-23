package cn.iocoder.yudao.module.system.service.parkingpayunion.impl;

/**
 * Created by xiaojie.lin on 2024/6/27.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/27
 */

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo.ChannelSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.ChannelDO;
import cn.iocoder.yudao.module.system.dal.mysql.parkingpayunion.ChannelMapper;
import cn.iocoder.yudao.module.system.service.parkingpayunion.ChannelService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.CHANNEL_NOT_EXISTS;

@Service
@Validated
public class ChannelServiceImpl implements ChannelService{

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public Integer createChannel(ChannelSaveReqVO createReqVO) {
        // 插入
        ChannelDO channel = BeanUtils.toBean(createReqVO, ChannelDO.class);
        channelMapper.insert(channel);
        // 返回
        return channel.getId();
    }

    @Override
    public void updateChannel(ChannelSaveReqVO updateReqVO) {
        // 校验存在
        validateChannelExists(updateReqVO.getId());
        // 更新
        ChannelDO updateObj = BeanUtils.toBean(updateReqVO, ChannelDO.class);
        channelMapper.updateById(updateObj);
    }

    @Override
    public void deleteChannel(Integer id) {
        // 校验存在
        validateChannelExists(id);
        // 删除
        channelMapper.deleteById(id);
    }

    private void validateChannelExists(Integer id) {
        if (channelMapper.selectById(id) == null) {
            throw exception(CHANNEL_NOT_EXISTS);
        }
    }

    @Override
    public ChannelDO getChannel(Integer id) {
        return channelMapper.selectById(id);
    }

    @Override
    public PageResult<ChannelDO> getChannelPage(ChannelPageReqVO pageReqVO) {
        return channelMapper.selectPage(pageReqVO);
    }

}
