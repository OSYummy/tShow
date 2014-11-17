package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONArray;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

final class ContactJSONImpl implements Contact, Serializable {
    private Group[] groups;
    private Response response;

    ContactJSONImpl(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    ContactJSONImpl(JSONObject jsonObject) {
        init(jsonObject);
    }

    private void init(JSONObject jsonObject) {
        response = new ResponseJSONImpl(jsonObject);

        if (!jsonObject.isNull("groups")) {
            JSONArray array = jsonObject.getJSONArray("groups");

            int size = array.length();
            groups = new Group[size];
            for (int i=0; i<size; i++){
                groups[i] = new GroupJSONImpl(array.getJSONObject(i));
            }
        } else if (!jsonObject.isNull("group")) {
            groups = new Group[] {
                    new GroupJSONImpl(jsonObject)
            };
        } else if (!jsonObject.isNull("groupid")) {
            groups = new Group[] {
                    new GroupJSONImpl(jsonObject)
            };
        } else {
            groups = new GroupJSONImpl[] {};
        }
    }

    static Contact createContact(HttpResponse response) throws IOException {
        return new ContactJSONImpl(response);
    }

    @Override public Group[] getGroups() {
        return groups;
    }

    @Override public Response getResponse() {
        return response;
    }

    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (groups!=null? Arrays.hashCode(groups): 0);
        result = 31*result + (response!=null? response.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;

        ContactJSONImpl that = (ContactJSONImpl)o;

        if (!Arrays.equals(groups, that.groups))
            return false;
        if (response!=null? !response.equals(that.response): that.response!=null)
            return false;

        return true;
    }

    @Override public String toString() {
        if (response != null) {
            return response.toString();
        } else {
            return "{\"groups\": []}";
        }
    }
}
