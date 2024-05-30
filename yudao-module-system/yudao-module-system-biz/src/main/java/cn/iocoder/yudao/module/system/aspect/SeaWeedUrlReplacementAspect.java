package cn.iocoder.yudao.module.system.aspect;

import cn.iocoder.yudao.module.system.controller.admin.arbi.sourceapplicantinfo.vo.SourceApplicantInfoRespVO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.lin on 2024/5/29.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/5/29
 */
@Aspect
@Component
public class SeaWeedUrlReplacementAspect{
    @Pointcut("@annotation(cn.iocoder.yudao.module.system.annotation.SeaWeedUrlReplacement)")
    public void seaWeedUrlReplacementPointcut() {}

    @AfterReturning(pointcut = "seaWeedUrlReplacementPointcut()", returning = "result")
    public void replaceUrl(JoinPoint joinPoint, Object result) {
        if (result != null) {
            // 这里你需要判断实体类中的字段，然后进行相应的替换逻辑
            if (result instanceof SourceApplicantInfoRespVO) {
                SourceApplicantInfoRespVO respVO = (SourceApplicantInfoRespVO) result;
                String modifiedUrl1 = replaceUrlForSeaweedFS(respVO.getIdentificationImgUrl());
                respVO.setIdentificationImgUrl(modifiedUrl1);

                String modifiedUrl2 = replaceUrlForSeaweedFS(respVO.getLegalPersonImgUrl());
                respVO.setLegalPersonImgUrl(modifiedUrl2);

                String modifiedUrl3 = replaceUrlForSeaweedFS(respVO.getBusinessLicenseUrl());
                respVO.setBusinessLicenseUrl(modifiedUrl3);

                String modifiedUrl4 = replaceUrlForSeaweedFS(respVO.getLegalPersonIdCertificateUrl());
                respVO.setLegalPersonIdCertificateUrl(modifiedUrl4);

                String modifiedUrl5 = replaceUrlForSeaweedFS(respVO.getAuthorizationLetterUrl());
                respVO.setAuthorizationLetterUrl(modifiedUrl5);

                String modifiedUrl6 = replaceUrlForSeaweedFS(respVO.getLawFirmLetterUrl());
                respVO.setLawFirmLetterUrl(modifiedUrl6);

                String modifiedUrl7 = replaceUrlForSeaweedFS(respVO.getLawyerLicenseUrl());
                respVO.setLawyerLicenseUrl(modifiedUrl7);
            } /*else if (entity instanceof AnotherEntity) {
                AnotherEntity anotherEntity = (AnotherEntity) entity;
                // 处理 AnotherEntity 中的字段替换逻辑
            }*/
            // 可以在这里添加更多的实体类处理逻辑
        }
    }

    private String replaceUrlForSeaweedFS(String oriUrl) {
        if(StringUtils.isEmpty(oriUrl)) {
            return null;
        }
        // 使用正则表达式替换
        return oriUrl.replaceAll("jf\\.parkmas\\.com:(\\d{3})99", "jf.parkmas.com:$133")
                .replaceAll("jf\\.puzek\\.com:(\\d{3})99", "jf.puzek.com:$133");
    }
}
