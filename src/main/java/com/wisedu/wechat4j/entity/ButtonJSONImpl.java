package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ButtonJSONImpl implements Button, Serializable {
    private String name;

    private ButtonType type;

    private String key;

    private String url;

    private List<Button> subButton;

    ButtonJSONImpl(JSONObject json) {
        init(json);
    }

    private void init(JSONObject json){
        if (!json.isNull("name")){
            this.name = json.getString("name");
        }
        if (!json.isNull("sub_button")){
            JSONArray list = json.getJSONArray("sub_button");

            int size = list.length();
            subButton = new ArrayList<Button>(size);
            for (int i=0; i<size; i++){
                subButton.add(
                        new SubButtonJSONImpl(list.getJSONObject(i))
                );
            }
        } else {
            subButton = Collections.EMPTY_LIST;
        }
        if (!json.isNull("type")){
            this.type = ButtonType.getInstance(json.getString("type"));
        }
        if (!json.isNull("key")){
            this.key = json.getString("key");
        }
        if (!json.isNull("url")){
            this.url = json.getString("url");
        }
    }

    static List<Button> createButtonList(HttpResponse response) throws WechatException{
        try {
            JSONObject json = response.asJSONObject();
            return createButtonList(
                    json.getJSONObject("menu").getJSONArray("button")
            );
        } catch (IOException ioe){
            throw new WechatException(ioe);
        }
    }

    static List<Button> createButtonList(JSONArray list) throws WechatException{
        try {
            int size = list.length();
            List<Button> menuList = new ArrayList<Button>();
            for (int i=0; i<size; i++){
                JSONObject json = list.getJSONObject(i);
                Button menu = new ButtonJSONImpl(json);
                menuList.add(menu);
            }
            return menuList;
        } catch (JSONException jsne){
            throw new WechatException(jsne);
        }
    }

    @Override public ButtonType getButtonType() {
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

    @Override public List<Button> getSubButton() {
        return subButton;
    }

    @Override public int hashCode(){
        int result = 0;
        result = result*31 + (name!=null? name.hashCode(): 0);
        result = result*31 + (type!=null? type.hashCode(): 0);
        result = result*31 + (key!=null? key.hashCode(): 0);
        result = result*31 + (url!=null? url.hashCode(): 0);
        result = result*31 + (subButton!=null? subButton.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o){
        if (o == this) return true;
        if (o==null || o.getClass()!=this.getClass()) return false;

        ButtonJSONImpl that = (ButtonJSONImpl)o;
        if (name!=null? !name.equals(that.name): that.name!=null)
            return false;
        if (type != that.type)
            return false;
        if (key!=null? !key.equals(that.key): that.key!=null)
            return false;
        if (url!=null? !url.equals(that.url): that.url!=null)
            return false;
        if (subButton!=null? !subButton.equals(that.subButton): that.subButton!=null)
            return false;
        return true;
    }

    @Override public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"name\": " + (name!=null? "\"" + name + "\"": null) + ",");
        sb.append("\"type\": " + (type!=null? "\"" + type.toString() + "\"": null) + ",");
        sb.append("\"key\": " + (key!=null? "\"" + key + "\"": null) + ",");
        sb.append("\"url\": " + (url!=null? "\"" + url + "\"": null) + ",");
        sb.append("\"sub_button\": [");
        for (int i=0; i<subButton.size(); i++){
            sb.append(subButton.get(i).toString());
            if (i < subButton.size()-1){
                sb.append(",");
            }
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
