package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-16
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Title", "Description", "PicUrl", "Url"})
public class Article {
    // 图文消息标题
    @XmlElement(name = "Title", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Title;

    // 图文消息描述
    @XmlElement(name = "Description", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Description;

    // 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    @XmlElement(name = "PicUrl", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String PicUrl;

    // 点击图文消息跳转链接
    @XmlElement(name = "Url", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Url;

    public Article() {

    }

    public Article(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
