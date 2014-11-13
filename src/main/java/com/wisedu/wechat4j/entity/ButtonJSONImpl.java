package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONArray;
import com.wisedu.wechat4j.internal.json.JSONException;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class ButtonJSONImpl implements Button, Serializable {
    private static final long serialVersionUID = 1822233292916572124L;

    private String name;

    private ButtonType type;

    private String key;

    private String url;

    private SubButton[] subButtons;

    private JSONObject object;

    ButtonJSONImpl(JSONObject jsonObject) {
        init(jsonObject);
    }

    private void init(JSONObject jsonObject){
        this.object = jsonObject;
        if (!object.isNull("type")){
            this.type = ButtonType.getInstance(object.getString("type"));
        }
        if (!object.isNull("name")){
            this.name = object.getString("name");
        }
        if (!object.isNull("key")){
            this.key = object.getString("key");
        }
        if (!object.isNull("url")){
            this.url = object.getString("url");
        }
        if (!object.isNull("sub_button")){
            JSONArray array = object.getJSONArray("sub_button");

            int size = array.length();
            subButtons = new SubButton[size];
            for (int i=0; i<size; i++){
                subButtons[i] = new SubButtonJSONImpl(array.getJSONObject(i));
            }
        } else {
            subButtons = new SubButton[]{};
        }
    }

    @Override public ButtonType getType() {
        return type;
    }

    @Override public String getName() {
        return name;
    }

    @Override public String getKey() {
        return key;
    }

    @Override public String getURL() {
        return url;
    }

    @Override public SubButton[] getSubButton() {
        return subButtons;
    }

    @Override public int hashCode(){
        int result = 0;
        result = result*31 + (type!=null? type.hashCode(): 0);
        result = result*31 + (name!=null? name.hashCode(): 0);
        result = result*31 + (key!=null? key.hashCode(): 0);
        result = result*31 + (url!=null? url.hashCode(): 0);
        result = result*31 + (subButtons!=null? Arrays.hashCode(subButtons): 0);
        return result;
    }

    @Override public boolean equals(Object o){
        if (o == this) return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;

        ButtonJSONImpl that = (ButtonJSONImpl)o;

        if (name!=null? !name.equals(that.name): that.name!=null)
            return false;
        if (type != that.type)
            return false;
        if (key!=null? !key.equals(that.key): that.key!=null)
            return false;
        if (url!=null? !url.equals(that.url): that.url!=null)
            return false;
        if (subButtons!=null? !Arrays.equals(subButtons, that.subButtons): that.subButtons!=null)
            return false;

        return true;
    }

    @Override public String toString(){
        if (object != null) {
            return object.toString();
        } else {
            return "{}";
        }
    }
}
