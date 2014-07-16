package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-16
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Music")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Title", "Description", "MusicUrl", "HQMusicUrl", "ThumbMediaId"})
public class Music {
    // 音乐标题
    @XmlElement(name = "Title", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Title;

    // 音乐描述
    @XmlElement(name = "Description", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Description;

    // 音乐链接
    @XmlElement(name = "MusicUrl", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MusicUrl;

    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
    @XmlElement(name = "HQMusicUrl", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String HQMusicUrl;

    // 缩略图的媒体id，通过上传多媒体文件，得到的id
    @XmlElement(name = "ThumbMediaId", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String ThumbMediaId;

    public Music() {

    }

    public Music(String title, String description, String musicUrl, String HQMusicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicUrl;
        this.HQMusicUrl = HQMusicUrl;
        ThumbMediaId = thumbMediaId;
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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
