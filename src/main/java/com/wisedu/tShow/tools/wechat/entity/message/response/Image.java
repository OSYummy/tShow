package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-16
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Image")
@XmlAccessorType(XmlAccessType.FIELD)
public class Image {
    // 通过上传多媒体文件，得到的id
    @XmlElement(name = "MediaId", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MediaId;

    public Image() {

    }

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
