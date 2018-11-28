package cn.bsit.util;


import java.util.List;

/**
 * ascii码 bcd 码转换的工具类
 *
 * @author yt.yin
 * @version 1.0 2017-08-15  22:26:05
 */
public class CodeConvertUtils {

    private final static byte[] hex = "0123456789ABCDEF".getBytes();

    private final static String[] binaryArray = {
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }

    /**
     * 字节数组转换成十六进制字符串
     *
     * @param b
     * @return 返回转换后的字符串
     */
    public static String bytes2HexString(byte[] b) {
        byte[] buff = new byte[2 * b.length];
        for (int i = 0; i < b.length; i++) {
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[b[i] & 0x0f];
        }
        return new String(buff);
    }

    /**
     * 从十六进制字符串到字节数组转换
     *
     * @param hexstr
     * @return 返回转换后的字节数组
     */
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 普通字符串转成十六进制字符串
     *
     * @param str
     * @return 转换后的十六进制字符串
     */
    public static String String2HexString(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转成普通字符串
     *
     * @param hexStr
     * @return 转换后的普通字符串
     */
    public static String HexString2String(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 位图信息（字节码）解析成二进制字符串
     *
     * @param bArray
     * @return 返回解析后的二进制字符串
     */
    private static String bytes2BinaryStr(byte[] bArray) {
        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            // 高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            // 低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;
    }

    /**
     * byte[] 转int,高位在前
     *
     * @param bytes 字节数组
     * @return 返回 int 值
     */
    public static int bytes2int(byte[] bytes){
        int result = 0;
        String string = new String(bytes);
        try {
            result = Integer.valueOf(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  ASCII 字节压缩成 BCD
     *
     * @param asc
     * @return 返回压缩后的 bcd字节码
     */
    public static byte ASCII_To_BCD(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9')) {
            bcd = (byte) (asc - '0');
        } else if ((asc >= 'A') && (asc <= 'F')) {
            bcd = (byte) (asc - 'A' + 10);
        } else if ((asc >= 'a') && (asc <= 'f')) {
            bcd = (byte) (asc - 'a' + 10);
        } else {
            bcd = (byte) (asc - 48);
        }
        return bcd;
    }

    /**
     * ASCII 字节码转成 BCD
     *
     * @param ascii
     * @return bcd 返回转好的bcd字节数组
     */
    public static byte[] ASCII_To_BCD(byte[] ascii) {
        int asc_len = ascii.length;
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = ASCII_To_BCD(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : ASCII_To_BCD(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    /**
     * BCD码解压缩成string
     *
     * @param bytes
     * @return 返回解压好的字符串
     */
    public static String BCD_To_Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    public static byte[] sumList(List<byte[]> list) {
        int length = 0;
        for (byte[] b : list) {
            length += b.length;
        }
        byte[] bb = new byte[length];
        int index = 0;
        for (byte[] b : list) {
            System.arraycopy(b, 0, bb, index, b.length);
            index += b.length;
        }
        return bb;
    }

    /**
     * 两个字节数组按位进行异或
     *
     * @param a 字节数组 a
     * @param b 字节数组 b
     * @return rb 返回处理后的字节数组
     */
    public static byte[] xor(byte[] a, byte[] b) {
        byte[] rb = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            rb[i] = (byte) (a[i] ^ b[i]);
        }
        return rb;
    }
    /**
     * 将b字节数组拼接到a字节数组末尾,生成大的字节数组
     * @param a
     * @param b
     */
    public static byte[] joinByteBToBytesAEnd(byte[] a, byte[] b)
    {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

}
