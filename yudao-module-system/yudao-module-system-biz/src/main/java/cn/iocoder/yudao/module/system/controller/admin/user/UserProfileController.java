package cn.iocoder.yudao.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.evidence.EvidenceBarn;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.CUserProfileRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.CuserPlateVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.user.UserConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.PostDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.RoleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.social.SocialUserDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.dept.PostService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import cn.iocoder.yudao.module.system.service.permission.RoleService;
import cn.iocoder.yudao.module.system.service.social.SocialUserService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.singleton;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_UPLOAD_FAIL;


@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController{

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private SocialUserService socialService;

    @Value("${parkingInfoEntryService.url}")
    private String parkingInfoEntryServiceUrl;

    @Value("${seaweedfs.url_prefix}")
    private String seaweedfsUrlPrefix;

    @Value("${seaweedfs.inner_server}")
    private String seaweedfsInnerServer;

    @Value("${seaweedfs.outer_server}")
    private String seaweedfsOuterServer;

    public static final DateTimeFormatter Formatter_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter Formatter_HHmmssSSS = DateTimeFormatter.ofPattern("HHmmssSSS");

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> profile(){
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        UserProfileRespVO resp = UserConvert.INSTANCE.convert03(user);
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        resp.setRoles(UserConvert.INSTANCE.convertList(userRoles));
        // 获得部门信息
        if(user.getDeptId() != null){
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(UserConvert.INSTANCE.convert02(dept));
        }
        // 获得岗位信息
        if(CollUtil.isNotEmpty(user.getPostIds())){
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(UserConvert.INSTANCE.convertList02(posts));
        }
        // 获得社交用户信息
        List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        resp.setSocialUsers(UserConvert.INSTANCE.convertList03(socialUsers));
        return success(resp);
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO){
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO){
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户个人头像")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }


    @GetMapping("/getCUserInfo")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<CUserProfileRespVO> getCUserInfo(){
        CUserProfileRespVO respVO = new CUserProfileRespVO();
        Long userId = getLoginUserId();
        AdminUserDO userDO = userService.getUser(userId);
        Integer roleId = getRoleId(userId);
        if(roleId != null){
            respVO.setRoleId(roleId);
        }else{
            return success(null);
        }

        respVO.setUserId(userId);
        respVO.setMobile(userDO.getMobile());
        CuserPlateVO cuserPlateVO = getCUserPlateInfo(userId);

        if(cuserPlateVO != null) {
            respVO.setCarLicenseImageUrl(cuserPlateVO.getCarLicenseImageUrl());
            respVO.setVerifiedStatus(cuserPlateVO.getVerifiedStatus());
            respVO.setPlateNum(cuserPlateVO.getPlateNum());
            respVO.setPlateColor(cuserPlateVO.getPlateColor());
        }

        return success(respVO);
    }

    @PostMapping("/updateVerifiedStatus")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<String> updateVerifiedStatus(@RequestBody Map<String, Object> requestBody){

        // 获取userId和verifiedStatus参数
        Integer userId = (Integer) requestBody.get("userId");
        Integer verifiedStatus = (Integer) requestBody.get("verifiedStatus");

        if(userId == null || verifiedStatus == null){
            throw exception(new ErrorCode(1001003005, "userId，认证结果至少有一个没有传入"));
        }
        String remoteUrl = parkingInfoEntryServiceUrl + "updateVerifiedStatus";
        Map<String, Object> paramMap = new HashedMap();
        paramMap.put("userId", userId);
        paramMap.put("verifiedStatus", verifiedStatus);

        String resultJson = HttpUtil.post(remoteUrl, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(resultJson);
        String result2 = jsonObject.get("status").toString();

        if("100".equals(result2)){
            return success("");
        }else{
            return error(new ErrorCode(1001003006, jsonObject.get("message").toString()));
        }
    }


    // 上传行驶证等信息
    @RequestMapping(value = "/uploadCarLicense", method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传行驶证图片")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<String> uploadCarLicense(/*@RequestParam("carLicenseImg1") */MultipartFile[] file,
//                                                 @RequestParam("carLicenseImg2") MultipartFile file2,
                                                                                     @RequestParam(value = "plateNum",
                                                                                             required = false) String plateNum,
                                                                                     @RequestParam(value = "plateColor",
                                                                                             required = false) String plateColor,
                                                                                     @RequestParam(value = "userId", required =
                                                                                             false) Integer userId) throws Exception{
        if(file == null || file.length == 0){
            throw exception(FILE_IS_EMPTY);
        }

        if(StringUtils.isEmpty(plateNum) || StringUtils.isEmpty(plateColor) || userId == null){
            throw exception(new ErrorCode(1001003004, "userId，车牌号或车牌颜色至少有一个没有传入"));
        }

        List<String> imgUrlList = new ArrayList<>();
        // 上传行驶证图片 至少一张， 可能由两张
        for(MultipartFile image : file){
            String imgUrl = doUploadCarLicense(image);
            imgUrlList.add(imgUrl);
        }

        String imgUrl_final = String.join(",", imgUrlList);
        updateCUserPlateInfo(userId, plateNum, plateColor, imgUrl_final);

        return success("车主资料上传成功");
    }

    private void updateCUserPlateInfo(Integer userId, String plateNum, String plateColor, String imgUrl) throws Exception{
        String remoteUrl = parkingInfoEntryServiceUrl + "updateCUserPlate";
        Map<String, Object> paramMap = new HashedMap();
        paramMap.put("userId", userId);
        paramMap.put("plateNum", plateNum);
        paramMap.put("plateColor", plateColor);
        paramMap.put("imgUrl", imgUrl);

        String resultJson = HttpUtil.post(remoteUrl, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(resultJson);
        String result2 = jsonObject.get("status").toString();

        if(!"100".equals(result2)){
            throw exception(new ErrorCode(1001003007, jsonObject.get("message").toString()));
        }

//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        String result2 = jsonObject.get("data").toString();
    }

    // 上传行驶证照片， 返回url
    private String doUploadCarLicense(MultipartFile file){
        String fileOriginalFilename = file.getOriginalFilename();
        String fileExtension = fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf(".") + 1);

        File tempFile;
        try{
            tempFile = File.createTempFile("temp", null);
            file.transferTo(tempFile);
        }catch(IOException e){
            throw exception(FILE_UPLOAD_FAIL);
        }

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        String dateStringForNow = now.format(Formatter_yyyyMMdd);
        String timeStringForNow = now.format(Formatter_HHmmssSSS);

        String targetUrl = seaweedfsUrlPrefix + dateStringForNow + "/" + timeStringForNow + "." + fileExtension;
        String finalImageUrl = targetUrl.replace(seaweedfsInnerServer, seaweedfsOuterServer);


        // 内网传输
        HttpRequest request =
                HttpUtil.createRequest(Method.POST, targetUrl) //
                        // 替换为SeaweedFS服务器的地址和端口以及上传路径
                        .form("file", tempFile);

        HttpResponse response = request.execute();
        if(!response.isOk()){
            throw exception(FILE_UPLOAD_FAIL);
        }

        // 保存外网url
        return finalImageUrl;
    }


    /**
     * 获取C端审核列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getPendingApprovalList") // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "获取审核列表")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<List<CUserProfileRespVO>> getPendingApprovalList() throws Exception{
        Long userId = getLoginUserId();
        Set<Long> roleIds = permissionService.getUserRoleIdListByUserId(userId);
        if(!(roleIds.contains(998L) || roleIds.contains(997L))){
            throw exception(new ErrorCode(1001003008, "当前用户不是审核员或管理员"));
        }

        String url = parkingInfoEntryServiceUrl + "getPendingApprovalList";
        String result = HttpUtil.get(url);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        String result2 = jsonObject.get("data").toString();
        // todo 报错解析
        if(result2 == "null") {
            return success(Collections.emptyList());
        }
        JSONArray jsonArray = JSONUtil.parseArray(result2);

        if(jsonArray.size() == 0){
            return success(Collections.emptyList());
        }

        List<CUserProfileRespVO> finalResult = new ArrayList<>();
        for(Object tempObj : jsonArray){
            JSONObject tempObj2 = (JSONObject) tempObj;
            CUserProfileRespVO cuserPlateVO = JSONUtil.toBean(tempObj2, CUserProfileRespVO.class);
            finalResult.add(cuserPlateVO);
        }

        return success(finalResult);
    }


    /**
     * 获取征信证据列表, 用户是否是已审核用户由parkingInfoEntryService里判断
     * @param requestBody
     * @return
     * @throws Exception
     */
    @PostMapping("/getEvidenceByPlateNum") // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "获取征信证据列表")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<List<EvidenceBarn>> getEvidenceByPlateNum(@RequestBody Map<String, Object> requestBody) throws Exception{
        Map<String, Object> paramMap = new HashedMap();
        Long userId = getLoginUserId();

        String plateNum = (String) requestBody.get("plateNum");
        paramMap.put("plateNum", plateNum);

        paramMap.put("userId", userId);
        String remoteUrl = parkingInfoEntryServiceUrl + "getEvidenceByPlateNum";
        String result = HttpUtil.post(remoteUrl, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        String result2 = jsonObject.get("data").toString();

        // todo 报错解析
        if(result2 == "null") {
            return success(Collections.emptyList());
        }
        JSONArray jsonArray = JSONUtil.parseArray(result2);

        if(jsonArray.size() == 0){
            return success(Collections.emptyList());
        }

        List<EvidenceBarn> finalResult = new ArrayList<>();
        for(Object tempObj : jsonArray){
            JSONObject tempObj2 = (JSONObject) tempObj;
            EvidenceBarn evidenceBarn = JSONUtil.toBean(tempObj2, EvidenceBarn.class);
            finalResult.add(evidenceBarn);
        }

        return success(finalResult);
    }

    /**
     * 获取登录用户的用户-车牌表数据
     * @param userId
     * @return
     */
    private CuserPlateVO getCUserPlateInfo(Long userId){
        Map<String, Object> paramMap = new HashedMap();
        paramMap.put("userId", userId);
        String url = parkingInfoEntryServiceUrl + "getCUserPlateInfo";
        String result = HttpUtil.post(url, paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        String result2 = jsonObject.get("data").toString();
        if(result2 == "null") {
            return null;
        }
        CuserPlateVO cuserPlateVO = JSONUtil.toBean(result2, CuserPlateVO.class);
        return cuserPlateVO;
    }

    private Boolean isCurrentCUserVerified() {
        Long userId = getLoginUserId();
        CuserPlateVO cuserPlateVO = getCUserPlateInfo(userId);
        if(cuserPlateVO != null && cuserPlateVO.getVerifiedStatus() != null && cuserPlateVO.getVerifiedStatus() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 获取登录用户的roleId
     * @return
     */
    private Integer getRoleId(Long userId) {
        if(userId == null) {
            userId = getLoginUserId();
        }
        Set<Long> roleIds = permissionService.getUserRoleIdsFromCache(userId, singleton(CommonStatusEnum.ENABLE.getStatus()));
        Integer roleId = null;
//        Set<Long> roleIds = permissionService.getUserRoleIdListByUserId(userId);
        if(roleIds.contains(997L)){
            // 管理员
            roleId = 997;
        }else if(roleIds.contains(998L)){
            // 审核员
            roleId = 998;
        }else if(roleIds.contains(999L)){
            // 车主
            roleId = 999;
        }else{
            roleId = null;
        }
        return roleId;
    }


}
