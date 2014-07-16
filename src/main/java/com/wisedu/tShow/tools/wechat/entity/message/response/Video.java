package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-16
 * Time: 上午11:40
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Video")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"MediaId", "Title", "Description"})
public class Video {
    // 通过上传多媒体文件，得到的id
    @XmlElement(name = "MediaId", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MediaId;

    // 视频消息的标题
    @XmlElement(name = "Title", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Title;

    // 视频消息的描述
    @XmlElement(name = "Description", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Description;

    public Video() {

    }

    public Video(String mediaId, String title, String description) {
        MediaId = mediaId;
        Title = title;
        Description = description;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
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
}
