package com.video.user.util;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 和密码有关的工具
 *
 * @author: master
 * @date: 2018/8/6
 */
public class EncryptionUtils {

    /**
     * 随机盐字符集
     */
    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 产生随机盐
     *
     * @param size 随机盐的位数大小
     * @return 返回随机盐
     */
    public static String randomSalt(int size) {
        // 产生伪随机数
        Random random = new Random();
        // 组合产生的随机字符
        StringBuilder builder = new StringBuilder();
        // 从零开始计算
        int charsSize = chars.length - 1;
        for (int index = 0; index < size; index++) {
            int randomNumber = random.nextInt(charsSize);
            builder.append(chars[randomNumber]);
        }
        return builder.toString();
    }

    /**
     * 随机盐加密
     *
     * @param password 需要加密的密码
     * @param salt     随机盐
     * @return 返回随机盐加密后的密码
     */
    public static String encryption(String password, String salt) {
        byte[] encryption = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordByte = (password.concat(salt)).getBytes();
            digest.update(passwordByte);
            encryption = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ByteUtils.toBinaryString(encryption);
    }

    /**
     * 直接信息加密
     *
     * @param info 需要加密的信息
     * @return 返回加密后的信息
     */
    public static String encryption(String info) {
        byte[] encryption = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordByte = info.getBytes();
            digest.update(passwordByte);
            encryption = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ByteUtils.toBinaryString(encryption);
    }
}
