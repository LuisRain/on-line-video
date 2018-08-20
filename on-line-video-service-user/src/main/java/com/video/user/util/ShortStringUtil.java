package com.video.user.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 字符串缩短工具(类似短网址服务)
 *
 * @author: master
 * @date: 2018/8/19
 */
public class ShortStringUtil {

    /**
     * 要使用生成短字符串的字符
     */
    private final static String[] chares = {
            "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 字符串编码
     */
    private final static String ENCODING = "utf-8";

    /**
     * 将生成的缩短字符串返回
     *
     * @param s
     * @return
     */
    public static String shortStringText(String s) {
        String hex = ShortStringUtil.encodeByMD5(s);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];
        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chares[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }
        Random random = new Random();
        return ShortStr[random.nextInt(4)];
    }

    /**
     * 将传递过来的字符串进行md5加密化
     *
     * @param originString
     * @return
     */
    private static String encodeByMD5(String originString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(originString.getBytes(ENCODING));
            byte[] result = messageDigest.digest();
            return TranscodingUtil.parseByte2HexStr(result);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
