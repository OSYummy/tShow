package com.wisedu.tShow.tools.wechat.client;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-23
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
class AccessToken {
    private String accessToken;

    AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
