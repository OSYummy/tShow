package com.wisedu.wechat4j.examples.menu;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.Wechat;
import com.wisedu.wechat4j.entity.Response;
import com.wisedu.wechat4j.internal.json.JSONObject;

public class CreateMenu {
    public static void main(String[] args) {
        try {
            Wechat wechat = Wechat.newInstance();
            Response response = wechat.createMenu(
                    new JSONObject(
                            "{\"button\":[{\"name\":\"今日歌曲\",\"sub_button\":[],\"type\":\"click\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"歌手简介\",\"sub_button\":[],\"type\":\"click\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"name\":\"搜索\",\"sub_button\":[],\"type\":\"view\",\"url\":\"http://www.soso.com/\"},{\"name\":\"视频\",\"sub_button\":[],\"type\":\"view\",\"url\":\"http://v.qq.com/\"},{\"name\":\"赞一下我们\",\"sub_button\":[],\"type\":\"click\",\"key\":\"V1001_GOOD\"}]}]}"
                    )
            );
            if (response.getErrCode() == 0) {
                System.out.println("Create Menu Succeed: " + response);
            } else {
               System.err.println("Create Menu Failed: " + response);
            }
        } catch (WechatException we) {
            we.printStackTrace();
        }
    }
}
