package com.video.user.util;

import com.video.user.bean.domain.UserToken;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Random;

/**
 * 和密码有关的工具
 *
 * @author: master
 * @date: 2018/8/6
 */
public class EncryptionUtils {

    @Value("${security.encrypt.key}")
    private static String key = "365FA2D3E754C07AC941695FAA9CE351B7144C1795AAE3894B7437C9513F0$%&#FD2";

    private final static String CODING = "utf-8";

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
    public static String encryption(String password, String salt) throws UnsupportedEncodingException {
        byte[] encryption = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder builder = new StringBuilder(password);
            builder.append(salt);
            byte[] passwordByte = (builder.toString()).getBytes(CODING);
            digest.update(passwordByte);
            encryption = digest.digest();
            return TranscodingUtil.parseByte2HexStr(encryption);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
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
            byte[] passwordByte = info.getBytes(CODING);
            digest.update(passwordByte);
            encryption = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ByteUtils.toBinaryString(encryption);
    }

    /**
     * 将用户userId和用户密码一起加密
     *
     * @param userId   用户的userId
     * @param password 用户的密码
     * @return 返回加密后的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String buildActivationCode(String userId, String password) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(userId).append(",").append(password);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(EncryptionUtils.key.getBytes(CODING)));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] encryptContent = builder.toString().getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(encryptContent);
            return TranscodingUtil.parseByte2HexStr(result);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析激活码
     *
     * @param activationCode 加密后的激活码
     * @return 解密后的激活码
     */
    public static String decryptionActivationCode(String activationCode) {
        byte[] activationCodeByte = TranscodingUtil.parseHexStr2Byte(activationCode);
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(EncryptionUtils.key.getBytes(CODING)));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(activationCodeByte);
            return TranscodingUtil.parseByte2HexStr(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
