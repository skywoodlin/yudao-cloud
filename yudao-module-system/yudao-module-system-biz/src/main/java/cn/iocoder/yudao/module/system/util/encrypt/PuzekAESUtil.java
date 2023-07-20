package cn.iocoder.yudao.module.system.util.encrypt;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Created by xiaojie.lin on 2023/4/11.
 *
 * @author xiaojie.lin
 * @description 描述
 * @date 2023/4/11
 */

/**
 * aes 加密的工具类
 * 1.存储 加密的秘钥key
 * 2.实现 aes 加密
 * 3.实现aes解密的功能
 *
 * @author xrp
 */
@Slf4j
@UtilityClass
public class PuzekAESUtil{
    /**
     *
     */
    private byte[] KEYS = "SHA@SZ@PUZEKBEST".getBytes(StandardCharsets.UTF_8);

    /**
     * 加密
     *
     * @param content content
     * @return String
     */
    public static String encrypt(String content){
        log.info("加密前：" + content);
        AES aes = SecureUtil.aes(KEYS);
        String encryptedStr = aes.encryptHex(content);
        log.info("加密后：" + encryptedStr);
        return encryptedStr;
    }

    /**
     * 解密
     *
     * @param content content
     * @return String
     */
    public static String decrypt(String content){
        log.info("解密前：" + content);
        if (null == content) {
            return null;
        }
        String decryptedStr = SecureUtil.aes(KEYS).decryptStr(content);
        log.info("解密后：" + decryptedStr);
        return decryptedStr;
    }
}
