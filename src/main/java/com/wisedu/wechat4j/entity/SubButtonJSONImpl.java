package com.wisedu.wechat4j.entity;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SubButtonJSONImpl implements Button, Serializable {
    private static final long serialVersionUID = 5620576057943907917L;

    private String name;
    private ButtonType type;
    private String key;
    private String url;
    private List<Button> subButton;

    public SubButtonJSONImpl(JSONObject json) {
        if (!json.isNull("name")){
            this.name = json.getString("name");
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
        if (!json.isNull("sub_button")){
            subButton = Collections.EMPTY_LIST;
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

    @Override public List<Button> getSubButton(){
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

        SubButtonJSONImpl that = (SubButtonJSONImpl)o;
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
        return "{"
                + "\"name\": " + (name!=null? "\"" + name + "\"": null) + ","
                + "\"type\": " + (type!=null? "\"" + type.toString() + "\"": null) + ","
                + "\"key\": " + (key!=null? "\"" + key + "\"": null) + ","
                + "\"url\": " + (url!=null? "\"" + url + "\"": null) + ","
                + "\"sub_button\": []"
                + "}";
    }
}
