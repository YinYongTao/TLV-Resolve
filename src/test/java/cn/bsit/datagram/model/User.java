package cn.bsit.datagram.model;

import cn.bsit.annotation.CodeType;
import cn.bsit.annotation.ModelAnnotation;

import java.util.Arrays;

/**
 * 用户实体类
 *
 * @author yt.yin
 * @version 1.0 2017-08-15  18:21:05
 */
public class User {

    /** 用户名 */
    @ModelAnnotation(placeholder = 1,length = 6,CODE_TYPE = CodeType.HexString)
    private String username;

    /** 密码 */
    @ModelAnnotation(placeholder = 2,length = 6,CODE_TYPE = CodeType.ByteArray)
    private byte [] password;

    /** 年龄,如果格式是 BCD length 是压缩后 BCD 字节数组的长度 */
    @ModelAnnotation(placeholder = 3,length = 1,CODE_TYPE = CodeType.BCD)
    private String age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"password\":")
                .append(Arrays.toString(password));
        sb.append(",\"age\":\"")
                .append(age).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
