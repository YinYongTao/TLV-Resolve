package cn.bsit.tlv;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 标准报文实体
 *
 * @author yt.yin
 * @version 1.0 2018-05-13  16:13:05
 */
public class StandardDatagram implements Serializable {

    /** 报文总长 4字节 小端转大端*/
    private int totalLength;

    /** tag  1个字节*/
    private String tag;

    /** 报文体总长度 */
    private int contentLength;

    /** 报文体 */
    private byte [] content;

    /** crc校验 */
    private String crc;

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"totalLength\":")
                .append(totalLength);
        sb.append(",\"tag\":\"")
                .append(tag).append('\"');
        sb.append(",\"contentLength\":")
                .append(contentLength);
        sb.append(",\"content\":")
                .append(Arrays.toString(content));
        sb.append(",\"crc\":\"")
                .append(crc).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
