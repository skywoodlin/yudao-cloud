package cn.iocoder.yudao.module.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by xiaojie.lin on 2024/6/4.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2024/6/4
 */
@Getter
@AllArgsConstructor
public enum DocInfoEnum {
    // 申请人受理立案通知书
    APPLICANTFILINGANDACCEPTANCE("申请人受理立案通知书", "applicantFilingAndAcceptance"),
    NOTICEOFACCEPTANCEOFARBITRATIONCASEBYAPPLICANT("申请人仲裁案件受理通知书", "noticeOfAcceptanceOfArbitrationCaseByApplicant"),
    APPLICANTSENDSNOTICEOFPROOF("申请人发送举证通知书", "applicantSendsNoticeOfProof"),
    APPLICANTARBITRATIONTRIBUNAL("申请人发送关于选定仲裁员和仲裁庭组成方式的函", "applicantArbitrationTribunal"),
    APPLICANTCONFIRMATIONLETTER("申请人发送仲裁文书送达地址的确认书", "applicantConfirmationLetter"),
    INVOICEINFORMATION("开票信息", "invoiceInformation"),
    APPLICANTDELIVERYRECEIPT("申请人送达回证", "applicantDeliveryReceipt"),

    // 被申请人受理立案通知书
    RESPONDENTFILINGANDACCEPTANCE("被申请人受理立案通知书", "respondentFilingAndAcceptance"),
    NOTICEARBITRATIONCASEBYRESPONDENT("被申请人仲裁案件通知书", "noticeArbitrationCaseByRespondent"),
    RESPONDENTSENDSNOTICEOFPROOF("被申请人发送举证通知书", "respondentSendsNoticeOfProof"),
    RESPONDENTARBITRATIONTRIBUNAL("被申请人发送关于选定仲裁员和仲裁庭组成方式的函", "respondentArbitrationTribunal"),
    RESPONDENTCONFIRMATIONLETTER("被申请人发送仲裁文书送达地址的确认书", "respondentConfirmationLetter"),
    RESPONDENTDELIVERYRECEIPT("被申请人送达回证", "respondentDeliveryReceipt"),

    // 预交费用通知书
    APPLICANTPREPAYNOTICE_LIST("预交费用通知书", "applicantPrepayNotice_list"),
    APPLICANTPREPAYNOTICE("预交费用通知书", "applicantPrepayNotice"),

    // 仲裁申请书
    ARBIAPPLY_LIST("仲裁申请书", "arbiApply_list"),
    ARBIAPPLY("仲裁申请书", "arbiApply"),

    // 收案登记表
    CASEREGISTRATIONFORM_LIST("收案登记表", "caseRegistrationForm_list"),
    CASEREGISTRATIONFORM("收案登记表", "caseRegistrationForm"),

    // 收案登记表
    EVIDENCE_LIST("证据清单", "evidence_list"),
    EVIDENCE("证据清单", "evidence");




    private final String title;
    private final String titleEn;
}
