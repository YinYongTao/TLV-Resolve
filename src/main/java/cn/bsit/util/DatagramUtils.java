package cn.bsit.util;

import java.util.Arrays;

/**
 * 工具类
 *
 * @author yt.yin
 * @version 1.0 2018-05-13  16:39:05
 */
public class DatagramUtils {

    /**
     * 将报文封装成带tag的byte数组,length表示长度字段的长度
     *
     * @param bt
     * @param length
     * @return
     */
    public static byte[] addLengthToBytes(byte[] bt, int length) {
        String lenStr = Integer.toString(bt.length, 16);
        int len = length * 2;
        if (lenStr.length() > len) {
            return null;
        }
        String s = prefixString(lenStr, "0", len);
        byte[] btLen = CodeConvertUtils.hexString2Bytes(s);
        byte[] fb = changeByteModel(btLen);

        byte[] finalBt = new byte[bt.length + length];
        System.arraycopy(fb, 0, finalBt, 0, fb.length);
        System.arraycopy(bt, 0, finalBt, fb.length, bt.length);

        return finalBt;
    }

    public static byte[] getFinalMessage(byte[] bt, byte[] tag) {
        byte[] fb = new byte[bt.length + 1];
        System.arraycopy(tag, 0, fb, 0, tag.length);
        System.arraycopy(bt, 0, fb, tag.length, bt.length);
        return addLengthToBytes(fb, 4);
    }

    /**
     * 大小端模式转换
     *
     * @param bt
     * @return
     */
    public static byte[] changeByteModel(byte[] bt) {
        byte[] normalBt = new byte[bt.length];
        for (int i = bt.length - 1; i >= 0; i--) {
            normalBt[bt.length - 1 - i] = bt[i];
        }
        return normalBt;
    }

    /**
     * 增加前缀
     */
    public static String prefixString(String s, String r, int num) {
        StringBuilder  stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(r);
        }
        int len = s.length();
        int n = stringBuilder.length() - len;
        return stringBuilder.substring(0, n) + s;
    }

    public static void main(String[] args) {
        byte [] bytes = {0, 0, 0, 9};
        byte[] model = changeByteModel(bytes);
        System.out.println(Arrays.toString(model));
    }

}
