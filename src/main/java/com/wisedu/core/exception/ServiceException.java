package com.wisedu.core.exception;

import java.io.PrintStream;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-20
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends RemoteException {
    private Throwable _cause;

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message);
        _cause = cause;
    }

    public Throwable getCause(){
        return _cause;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (getCause() == null) {
            return message;
        } else {
            return message + "; nested exception is: " + _cause;
        }
    }

    public void printStackTrace(){
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream ps){
        if (getCause() == null) {
            super.printStackTrace(ps);
        } else {
            ps.println(this);
            ps.print("Caused by: ");
            getCause().printStackTrace(ps);
        }
    }
}