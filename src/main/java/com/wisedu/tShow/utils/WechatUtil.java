package com.wisedu.tShow.utils;

import com.wisedu.core.common.utils.EncodeUtil;
import com.wisedu.core.common.utils.PropertyConfigurerUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class WechatUtil {
    public static String checkSignature(String signature, String timestamp, String nonce)
            throws NoSuchAlgorithmException{
        // 字典序排序;
        String[] plainText={
                (String) PropertyConfigurerUtil.getProperty("app.wechat.token"), timestamp, nonce
        };
        Arrays.sort(plainText);

        // SHA加密;
        String digest = EncodeUtil.getDigestOfString(
                EncodeUtil.SHA1, plainText[0] + plainText[1] + plainText[2]
        );

        return digest;
    }
}
