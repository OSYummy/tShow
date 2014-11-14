package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONArray;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;

final class ResponseJSONImpl implements Response{
    private Integer errCode;
    private String errMsg;

    protected JSONObject object;
    protected JSONArray array;

    ResponseJSONImpl(JSONObject jsonObject) {
        this.object = jsonObject;
        if (!object.isNull("errcode")) {
            this.errCode = jsonObject.getInt("errcode");
        }
        if (!object.isNull("errmsg")) {
            this.errMsg = jsonObject.getString("errmsg");
        }
    }

    ResponseJSONImpl(JSONArray jsonArray) {
        this.array = jsonArray;
    }

    static ResponseJSONImpl createResponse(HttpResponse response) throws IOException{
        if (response.asJSONObject() != null) {
            return new ResponseJSONImpl(response.asJSONObject());
        } else if (response.asJSONArray() != null) {
            return new ResponseJSONImpl(response.asJSONArray());
        } else {
            throw new IOException("Invalid Response Data.");
        }
    }

    @Override public final JSONObject getObject() {
        return object;
    }

    @Override public final JSONArray getArray() {
        return array;
    }

    @Override public Integer getErrCode(){
        return errCode;
    }

    @Override public String getErrMsg(){
        return errMsg;
    }

    @Override public int hashCode(){
        int result = 0;
        result = result*31 + (errCode!=null? errCode.hashCode(): 0);
        result = result*31 + (errMsg!=null? errMsg.hashCode(): 0);
        result = result*31 + (object!=null? object.hashCode(): 0);
        result = result*31 + (array!=null? array.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o){
        if (o == this) return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;

        ResponseJSONImpl that = (ResponseJSONImpl)o;
        if (errCode!=null? !errCode.equals(that.errCode): that.errCode!=null)
            return false;
        if (errMsg!=null? !errMsg.equals(that.errMsg): that.errMsg!=null)
            return false;
        if (object!=null? !object.equals(that.object): that.object!=null)
            return false;
        if (array!=null? !array.equals(that.array): that.array!=null)
            return false;
        return true;
    }

    @Override public String toString() {
        if (object != null) {
            return object.toString();
        } else if (array != null){
            return array.toString();
        } else {
            return "{\"errcode\": ,\"errmsg\":\"\"}";
        }
    }
}