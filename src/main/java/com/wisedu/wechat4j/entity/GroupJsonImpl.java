package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.Serializable;

final class GroupJSONImpl implements Group, Serializable {
    private Integer id;
    private String name;
    private Integer count;

    private JSONObject object;

    GroupJSONImpl(JSONObject jsonObject) {
        init(jsonObject);
    }

    private void init(JSONObject jsonObject) {
        this.object = jsonObject;
        if (!object.isNull("id")){
            this.id = object.getInt("id");
        }
        if (!object.isNull("name")){
            this.name = object.getString("name");
        }
        if (!object.isNull("count")){
            this.count = object.getInt("count");
        }
        if (!object.isNull("groupid")) {
            this.id = object.getInt("groupid");
        }
    }

    @Override public Integer getID() {
        return id;
    }

    @Override public String getName() {
        return name;
    }

    @Override public Integer getCount() {
        return count;
    }


    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (id!=null? id.hashCode(): 0);
        result = 31*result + (name!=null? name.hashCode(): 0);
        result = 31*result + (count!=null? count.hashCode(): 0);
        result = 31*result + (object!= null? object.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass())
            return false;

        GroupJSONImpl that = (GroupJSONImpl)o;
        if (count!=null? !count.equals(that.count): that.count!=null)
            return false;
        if (id!=null? !id.equals(that.id): that.id!=null)
            return false;
        if (name!=null? !name.equals(that.name): that.name!=null)
            return false;
        if (object!=null? !object.equals(that.object): that.object!=null)
            return false;
        return true;
    }

    @Override public String toString() {
        if (object != null) {
            return object.toString();
        } else {
            return "{}";
        }
    }
}
