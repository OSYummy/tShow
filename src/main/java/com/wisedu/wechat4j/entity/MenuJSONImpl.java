package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONArray;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

final class MenuJSONImpl extends ResponseJSONImpl implements Menu, Serializable {
    private static final long serialVersionUID = -176057211148577738L;

    private Button[] buttons;
    private JSONObject object;

    MenuJSONImpl(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    MenuJSONImpl(JSONObject jsonObject) {
        super(jsonObject);
        init(jsonObject);
    }

    private void init(JSONObject jsonObject) {
        this.object = jsonObject;
        if (!object.isNull("menu")) {
            JSONObject menu = jsonObject.getJSONObject("menu");
            if (!menu.isNull("button")) {
                JSONArray array = menu.getJSONArray("button");

                int size = array.length();
                buttons = new Button[size];
                for (int i=0; i<size; i++){
                    buttons[i] = new ButtonJSONImpl(array.getJSONObject(i));
                }
            } else {
                buttons = new Button[]{};
            }
        }
    }

    static Menu createMenu(HttpResponse response) throws IOException {
        return new MenuJSONImpl(response);
    }

    @Override public Button[] getButtons() {
        return buttons;
    }

    @Override public JSONObject getObject() {
        return object;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        if (!super.equals(o)) return false;

        MenuJSONImpl that = (MenuJSONImpl)o;

        if (!Arrays.equals(buttons, that.buttons))
            return false;
        if (object!= null? !object.equals(that.object): that.object!=null)
            return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31*result + (buttons!=null? Arrays.hashCode(buttons): 0);
        result = 31*result + (object!=null? object.hashCode(): 0);
        return result;
    }

    @Override public String toString() {
        if (object != null) {
            return object.toString();
        } else {
            return "{}";
        }
    }
}
