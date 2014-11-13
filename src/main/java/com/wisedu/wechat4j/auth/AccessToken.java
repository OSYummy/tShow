package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

public class AccessToken implements Serializable {
    private static final long serialVersionUID = -729542432095858168L;

    private Integer errCode;
    private String errMsg;

    private String token;
    private Long expires;

    private JSONObject object;

    public AccessToken(String token, Long expires) {
        this.token = token;
        this.expires = expires;
    }

    public AccessToken(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    public AccessToken(JSONObject jsonObject) {
        init(jsonObject);
    }

    private void init(JSONObject jsonObject) {
        this.object = jsonObject;
        if (!object.isNull("errcode")) {
            this.errCode = jsonObject.getInt("errcode");
        }
        if (!object.isNull("errmsg")) {
            this.errMsg = jsonObject.getString("errmsg");
        }
        if (!object.isNull("access_token")) {
            this.token = object.getString("access_token");
        }
        if (!object.isNull("expires_in")) {
            this.expires = object.getLong("expires_in");
        }
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getToken() {
        return token;
    }

    public Long getExpires() {
        return expires;
    }

    public JSONObject getObject() {
        return object;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;

        AccessToken that = (AccessToken)o;

        if (errCode!= null? !errCode.equals(that.errCode): that.errCode!=null)
            return false;
        if (errMsg!= null? !errMsg.equals(that.errMsg): that.errMsg!=null)
            return false;
        if (token!= null? !token.equals(that.token): that.token!=null)
            return false;
        if (expires!= null? !expires.equals(that.expires): that.expires!=null)
            return false;
        if (object!= null? !object.equals(that.object): that.object!=null)
            return false;

        return true;
    }

    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (errCode!=null? errCode.hashCode(): 0);
        result = 31*result + (errMsg!=null? errMsg.hashCode(): 0);
        result = 31*result + (token!=null? token.hashCode(): 0);
        result = 31*result + (expires!=null? expires.hashCode(): 0);
        result = 31*result + (object!=null? object.hashCode(): 0);
        return result;
    }

    @Override public String toString() {
        if (object != null){
            return object.toString();
        } else {
            return "{}";
        }
    }
}
