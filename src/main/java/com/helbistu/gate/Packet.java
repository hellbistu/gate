package com.helbistu.gate;

/**
 * 包结构
 */
public class Packet {

    //pdata长度,int类型,4个byte按小端模式（Little-endian）
    private int length = -1;

    //协议类型,short类型,2个byte按小端模式(最多支持32767个协议)
    private short ptype = -1;

    //协议数据, 例如:protobuf序列化后的byte数组
    private byte[] pdata;

    public int getLength() {
        return length;
    }

    public short getPtype() {
        return ptype;
    }

    public byte[] getPdata() {
        return pdata;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPtype(short ptype) {
        this.ptype = ptype;
    }

    public void setPdata(byte[] pdata) {
        this.pdata = pdata;
    }
}
