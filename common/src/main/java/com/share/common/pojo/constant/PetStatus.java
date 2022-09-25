package com.share.common.pojo.constant;

public enum PetStatus {
    OWN(1,"私有"),SHARE(0,"共享");
    private int status;
    private String msg;

    PetStatus(int status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
