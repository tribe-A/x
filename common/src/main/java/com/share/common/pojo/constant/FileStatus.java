package com.share.common.pojo.constant;

public enum FileStatus {
    DEFAULT(0,"上传完成");
    private int status;
    private String msg;

    FileStatus(int status,String msg) {
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
