package cn.bsit.datagram.core;

import cn.bsit.datagram.model.User;
import cn.bsit.tlv.TLVDatagramResolve;

/**
 * 测试类
 *
 * @author yt.yin
 * @version 1.0 2017-08-16  14:24:05
 */
public class TLVDatagramResolveTest {

    public static void main(String[] args) {

        byte [] bytes =new byte[]{0x6c,0x69,0x6d,0x69,0x6e,0x67,0x31,0x32,0x33,0x34,0x35,0x36,32};

        try {
            User user = TLVDatagramResolve.resolveWithVariableLength(bytes, new User());
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
