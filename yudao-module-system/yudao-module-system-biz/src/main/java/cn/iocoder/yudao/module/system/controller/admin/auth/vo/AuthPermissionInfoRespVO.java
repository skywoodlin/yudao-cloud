package cn.iocoder.yudao.module.system.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(description = "管理后台 - 登录用户的权限信息 Response VO，额外包括用户信息和角色列表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermissionInfoRespVO {

    @Schema(description = "用户信息", required = true)
    private UserVO user;

    @Schema(description = "角色标识数组", required = true)
    private Set<String> roles;

    @Schema(description = "操作权限数组", required = true)
    private Set<String> permissions;

    @Schema(description = "用户信息 VO")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserVO {

        @Schema(description = "用户编号", required = true, example = "1024")
        private Long id;

        @Schema(description = "用户昵称", required = true, example = "芋道源码")
        private String nickname;

        @Schema(description = "用户头像", required = true, example = "http://www.iocoder.cn/xx.jpg")
        private String avatar;

        @Schema(description = "appid", required = false, example = "12345333336")
        private Integer appid;

        @Schema(description = "secret", required = false, example = "asdfasfewrq")
        private String secret;

        @Schema(description = "sourceId", required = false, example = "1111")
        private Integer sourceId;

    }

}