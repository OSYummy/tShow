package com.wisedu;

import microsoft.exchange.webservices.data.AutodiscoverLocalException;
import microsoft.exchange.webservices.data.IAutodiscoverRedirectionUrl;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-27
 * Time: 下午8:05
 * To change this template use File | Settings | File Templates.
 */
public class callback implements IAutodiscoverRedirectionUrl {
    @Override
    public boolean autodiscoverRedirectionUrlValidationCallback(String s) throws AutodiscoverLocalException {
        return true;
    }
}
