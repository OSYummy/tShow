package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONObject;

abstract class ResponseJSONImpl implements Response{
    private Integer errCode;
    private String errMsg;

    ResponseJSONImpl(JSONObject jsonObject) {
        if (!jsonObject.isNull("errcode")) {
            this.errCode = jsonObject.getInt("errcode");
        }
        if (!jsonObject.isNull("errmsg")) {
            this.errMsg = jsonObject.getString("errmsg");
        }
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

        return true;
    }
}