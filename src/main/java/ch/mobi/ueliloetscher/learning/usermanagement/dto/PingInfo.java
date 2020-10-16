package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import java.io.Serializable;

public class PingInfo implements Serializable {
    private String info;

    public PingInfo() {
    }

    public PingInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}