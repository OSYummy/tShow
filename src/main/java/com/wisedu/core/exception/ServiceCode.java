package com.wisedu.core.exception;

public enum ServiceCode {
    SERVICE_ERROR(-1, "system error."),
    SERVICE_OK(0, "ok.");

    private Integer errCode;
    private String errMsg;

    private ServiceCode(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
