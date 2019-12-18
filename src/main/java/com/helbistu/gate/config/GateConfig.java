package com.helbistu.gate.config;

public class GateConfig {

    private boolean openKeepAlive = true;

    private int keepAliveSec = 30;

    private int publicPort = 28993;

    private String publicIp = "127.0.0.1";

    private int sndBuff = 65535;

    private int rcvBuff = 1024;

    private int backLog=1024;

    private boolean tcpNodelay = true;

    public boolean isOpenKeepAlive() {
        return openKeepAlive;
    }

    public int getKeepAliveSec() {
        return keepAliveSec;
    }

    public int getPublicPort() {
        return publicPort;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public int getSndBuff() {
        return sndBuff;
    }

    public int getRcvBuff() {
        return rcvBuff;
    }

    public int getBackLog() {
        return backLog;
    }

    public boolean isTcpNodelay() {
        return tcpNodelay;
    }
}
