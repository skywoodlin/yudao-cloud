package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.EvidenceBarn;

/**
 * Created by xiaojie.lin on 2023/8/3.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/8/3
 */
public class GetEvidenceBySourceIdRespVo extends EvidenceBarn{
    // 来源方名称
    private String sourceName;

    public String getSourceName(){
        return sourceName;
    }

    public void setSourceName(String sourceName){
        this.sourceName = sourceName;
    }
}
