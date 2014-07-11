package com.wisedu.tShow.app.wechat.bo;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo {
    // 是否订阅,0: 未关注，拉取不到其余信息; 1: 已关注
    private String subscribe;

    // 用户的标识，对当前公众号唯一
    private String openid;

    // 用户的昵称
    private String nickname;

    // 性别，1: 男性; 2: 女性; 0: 未知
    private String sex;

    // 用户所在城市
    private String city;

    // 用户所在国家
    private String country;

    // 用户所在省份
    private String province;

    // 用户的语言，简体中文为zh_CN
    private String language;

    // 用户头像
    private String headimgurl;

    // 用户关注时间
    private String subscribe_time;

    // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
    private String unionid;

    public UserInfo() {
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
