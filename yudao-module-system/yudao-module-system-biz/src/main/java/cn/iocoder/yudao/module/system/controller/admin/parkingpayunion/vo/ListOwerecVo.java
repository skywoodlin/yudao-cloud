package cn.iocoder.yudao.module.system.controller.admin.parkingpayunion.vo;

import cn.iocoder.yudao.module.system.dal.dataobject.parkingpayunion.Owerec;

import java.util.List;

/**
 * Created by xiaojie.lin on 2023/8/3.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/8/3
 */
public class ListOwerecVo extends Owerec{
    // 来源方名称
    private String sourceName;

    // 图片url
    private List<String> photoUrls;

    private List<Integer> evidenceIds;

    public String getSourceName(){
        return sourceName;
    }

    public void setSourceName(String sourceName){
        this.sourceName = sourceName;
    }

    public List<String> getPhotoUrls(){
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls){
        this.photoUrls = photoUrls;
    }

    public List<Integer> getEvidenceIds(){
        return evidenceIds;
    }

    public void setEvidenceIds(List<Integer> evidenceIds){
        this.evidenceIds = evidenceIds;
    }
}
