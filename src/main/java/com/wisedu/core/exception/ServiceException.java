package com.wisedu.core.exception;

import java.io.Serializable;

// Service Exception
public class ServiceException
        extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 953273024086278779L;

    private String errMsg;
    private Integer errCode;

    public ServiceException(){
        super();
    }

    public ServiceException(String errMsg){
        super(errMsg);
        this.errMsg = errMsg;
    }

    public ServiceException(ServiceCode serviceCode) {
        super(serviceCode.getErrMsg());
        this.errMsg = serviceCode.getErrMsg();
        this.errCode = serviceCode.getErrCode();
    }

    public ServiceException(String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errMsg = errMsg;
    }

    public ServiceException(String errMsg, Integer errCode) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;

        ServiceException that = (ServiceException)o;

        if (errMsg!=null? !errMsg.equals(that.errMsg): that.errMsg!=null) return false;
        if (errCode!=null? !errCode.equals(that.errCode): that.errCode!=null) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (errMsg!=null? errMsg.hashCode(): 0);
        result = 31*result + (errCode!=null? errCode.hashCode(): 0);
        return result;
    }
}

/*
public class ServiceException
        extends RuntimeException implements Serializable {
    private String errMsg;
    private Integer errCode;

    public ServiceException(){

    }

    public ServiceException(String errMsg){
        this.errMsg = errMsg;
    }

    public ServiceException(String errMsg, Throwable cause) {
        super(cause);
        this.errMsg = errMsg;
    }

    public ServiceException(Integer errCode, String errMsg) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    @Override public String getMessage() {
        Throwable cause = super.getCause();
        if (cause == null) {
            return this.errMsg;
        } else {
            return this.errMsg + "; nested exception is: " + cause;
        }
    }

    public void printStackTrace(){
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream ps){
        Throwable cause = super.getCause();
        if (cause != null) {
            ps.println(this);
            ps.print("Caused by: ");
            cause.printStackTrace(ps);
        } else {
            super.printStackTrace(ps);
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;

        ServiceException that = (ServiceException)o;

        if (errMsg!=null? !errMsg.equals(that.errMsg): that.errMsg!=null) return false;
        if (errCode!=null? !errCode.equals(that.errCode): that.errCode!=null) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (errMsg!=null? errMsg.hashCode(): 0);
        result = 31*result + (errCode!=null? errCode.hashCode(): 0);
        return result;
    }
}*/
