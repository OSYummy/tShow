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
@XmlRootElement(name = "Voice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Voice {
    // 通过上传多媒体文件，得到的id
    @XmlElement(name = "MediaId", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MediaId;

    public Voice() {

    }

    public Voice(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
