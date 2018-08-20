package com.video.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 解压缩工具类
 *
 * @author: master
 * @date: 2018/8/17
 */
public class GzipUtils {

    private final static String GZIP_ENCODE = "utf-8";

    /**
     * 字符串压缩工具方法
     *
     * @param str 需要压缩的字符春
     * @return 返回压缩后的字符串
     */
    public static String stringCondense(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(str.getBytes(GZIP_ENCODE));
            gzipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return byteArrayOutputStream.toString(GZIP_ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解压工具方法
     *
     * @param str 需要解压的字符串
     * @return 返回解压后的字符串
     */
    public static String decompressionString(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gzipInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(GZIP_ENCODE));
            gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipInputStream.read(buffer)) >= 0) {
                byteArrayOutputStream.write(buffer, 0, n);
            }
            gzipInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return byteArrayOutputStream.toString(GZIP_ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
