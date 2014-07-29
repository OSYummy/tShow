package com.wisedu.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {
    public final static String MD5= "md5";

    public final static String SHA1= "sha-1";

    /**
     * encode string
     *
     * @param algorithm
     * @param plaintext
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public static byte[] getDigest(String algorithm, String plaintext)
            throws NoSuchAlgorithmException{
        if (plaintext == null)
            throw new NullPointerException("Plain Text Can not be Null");

        MessageDigest md=MessageDigest.getInstance(algorithm);
        md.update(plaintext.getBytes());
        return md.digest();
    }

    public static String getDigestOfString(String algorithm, String plaintext)
            throws NoSuchAlgorithmException{
        byte[] bytes = getDigest(algorithm, plaintext);

        StringBuffer sb=new StringBuffer(bytes.length<<1);
        for (byte b: bytes){
            if ((b&0xf0) == 0) sb.append("0");
            sb.append(Integer.toHexString(b&0xff));
        }
        return sb.toString();
    }
}